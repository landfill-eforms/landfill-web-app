package org.lacitysan.landfill.server.security;

import org.lacitysan.landfill.server.persistence.dao.user.UserDao;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
	PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = (String)authentication.getPrincipal();
		User user = userDao.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid Username"); // TODO Change this to "Invalid user or password"
		}
		
		String password = (String)authentication.getCredentials();
		if (password == null || !passwordEncoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException("Invalid Password"); // TODO Change this to "Invalid user or password"
		}
		
		AuthenticatedUser result = new AuthenticatedUser(user.getId(), user.getUsername(), TokenAuthenticationUtil.userGroupToAuthorities(user.getUserGroups()));
		return result;
	}

}
