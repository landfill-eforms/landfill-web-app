package org.lacitysan.landfill.server.security;

import java.util.Arrays;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.config.app.vars.ApplicationVariableService;
import org.lacitysan.landfill.server.exception.user.DeactivatedUserException;
import org.lacitysan.landfill.server.persistence.dao.user.UserDao;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * Custom implementation of <code>AuthenticationManager</code>.
 * @author Alvin Quach
 */
@Component
public class AuthenticationManagerImpl implements AuthenticationManager {

	@Autowired
	UserDao userDao;
	
	@Autowired
	ApplicationVariableService applicationVariableService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	TokenAuthenticationService tokenAuthenticationService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		// Get username with whitespaces removed.
		String username = ((String)authentication.getPrincipal()).trim().replaceAll("\\s+", "");
		
		// Get password.
		String password = (String)authentication.getCredentials();
		
		AuthenticatedUser result;
		
		// If the user is attempting to log in with the super admin account.
		if (username.equalsIgnoreCase(ApplicationConstant.SUPER_ADMIN_USERNAME)) {
			if (password == null || !passwordEncoder.matches(password, applicationVariableService.getSuperAdminPassword())) {
				throw new BadCredentialsException("Invalid Password"); // TODO Change this to "Invalid user or password"
			}
			result = new AuthenticatedUser(-1, ApplicationConstant.SUPER_ADMIN_USERNAME, Arrays.asList(new GrantedAuthority[] {new GrantedAuthorityImpl(ApplicationConstant.SUPER_ADMIN_PERMISSION_NAME)}));
		}
		
		// If the user is attempting to log in with a normal user account.
		else {
		
			// Attempt to find user with matching username.
			User user = userDao.getUserByUsername(username);
			
			// If no user was found, then username is invalid.
			if (user == null) {
				throw new UsernameNotFoundException("Invalid Username"); // TODO Change this to "Invalid user or password"
			}
			
			// Validate password.
			if (password == null || !passwordEncoder.matches(password, user.getPassword())) {
				throw new BadCredentialsException("Invalid Password"); // TODO Change this to "Invalid user or password"
			}
			
			// Check if user is deactivated.
			if (!user.getEnabled()) {
				throw new DeactivatedUserException("This account has been deactivated.");
			}
			
			result = new AuthenticatedUser(user, tokenAuthenticationService.userGroupToAuthorities(user.getUserGroups())); 
		}
		
		return result;
	}

}
