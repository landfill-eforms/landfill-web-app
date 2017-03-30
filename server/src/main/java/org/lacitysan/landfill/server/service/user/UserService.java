package org.lacitysan.landfill.server.service.user;

import java.util.Map;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.config.app.vars.ApplicationVariableService;
import org.lacitysan.landfill.server.exception.AlreadyExistsException;
import org.lacitysan.landfill.server.exception.user.InvalidEmailException;
import org.lacitysan.landfill.server.exception.user.InvalidPasswordException;
import org.lacitysan.landfill.server.exception.user.InvalidUsernameException;
import org.lacitysan.landfill.server.persistence.dao.user.UserDao;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Alvin Quach
 */
@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	ApplicationVariableService applicationVariableService;
	
	/** 
	 * Gets the current user based on authentication stored in the security context holder.
	 * Super admin account will return null.
	 * @return
	 */
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null ) {
			return null;
		}
		Object principle = authentication.getPrincipal();
		if (principle instanceof Map) {
			Integer id = (Integer)((Map<?, ?>)principle).get("id");
			if (id != null && id >= 0) {
				return userDao.getById(id);
			}
		}
		return null;
	}
	
	public User create(User user) {
		
		// Check if username is valid.
		validateUsername(user.getUsername(), true);
		
		// Check if username already exists.
		checkIfUsernameExists(user.getUsername(), user.getId(), true);
		
		// Check if password is valid.
		validatePassword(user.getPassword(), true);
		
		// Encode password.
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		// Check if email address is valid.
		validateEmail(user.getEmailAddress(), true);
		
		user.setEnabled(true);
		userDao.create(user);
		return user;
		
	}
	
	public User changeUsername(User user) {
		
		// Check if username is valid.
		validateUsername(user.getUsername(), true);
		
		// Check if username already exists.
		checkIfUsernameExists(user.getUsername(), user.getId(), true);
		
		// Update existing user.
		User existing = userDao.getById(user.getId());
		if (existing == null) {
			// TODO Throw user ID not found exception.
		}
		existing.setUsername(user.getUsername());
		return userDao.update(existing);
		
	}
	
	public User changePassword(User user) {
		User existing = userDao.getById(user.getId());
		if (existing == null) {
			// TODO Throw user ID not found exception.
		}
		validatePassword(user.getPassword(), true);
		existing.setPassword(passwordEncoder.encode(user.getPassword()));
		return userDao.update(existing);
	}
	
	public User updateProfile(User user) {
		User existing = userDao.getById(user.getId());
		if (existing == null) {
			// TODO Throw user ID not found exception.
		}
		existing.setFirstname(user.getFirstname());
		existing.setMiddlename(user.getMiddlename());
		existing.setLastname(user.getLastname());
		if (validateEmail(user.getEmailAddress(), true)) {
			existing.setEmailAddress(user.getEmailAddress());
		}
		return userDao.update(existing);
	}
	
	public User updateEmployeeId(User user) {
		User existing = userDao.getById(user.getId());
		if (existing == null) {
			// TODO Throw user ID not found exception.
		}
		existing.setEmployeeId(user.getEmployeeId());
		return userDao.update(existing);
	}
	
	public User updateStatus(User user) {
		User existing = userDao.getById(user.getId());
		if (existing == null) {
			// TODO Throw user ID not found exception.
		}
		existing.setEnabled(user.getEnabled());
		return userDao.update(existing);
	}
	
	public User updateUserGroups(User user) {
		User existing = userDao.getById(user.getId());
		if (existing == null) {
			// TODO Throw user ID not found exception.
		}
		// TODO Check if current user has privileges to assign some restricted permissions (ie. Admin).
		existing.setUserGroups(user.getUserGroups());
		return userDao.update(existing);
	}
	
	/**
	 * Check username against username restrictions as imposed by the application properties.
	 * @param username The username to be checked.
	 * @param throwException Whether to throw an exception if the username is not valid.
	 * @return True if the username is valid, false otherwise.
	 */
	private boolean validateUsername(String username, boolean throwException) {
		if (username == null || username.length() < applicationVariableService.getUsernameMinLength()) {
			if (throwException) {
				throw new InvalidUsernameException("Username must be at least " + applicationVariableService.getUsernameMinLength() + " characters long.");
			}
			return false;
		}
		if (!username.matches("^[a-zA-Z0-9]*$")) {
			if (throwException) {
				throw new InvalidUsernameException("Username cannot contain spaces or special characters.");
			}
			return false;
		}
		return true;
	}
	
	/**
	 * Check password against password restrictions as imposed by the application properties.
	 * @param username The password to be checked.
	 * @param throwException Whether to throw an exception if the password is not valid.
	 * @return True if the password is valid, false otherwise.
	 */
	private boolean validatePassword(String password, boolean throwException) {
		if (password == null || password.length() < applicationVariableService.getPasswordMinLength()) {
			if (throwException) {
				throw new InvalidPasswordException("Password must be at least " + applicationVariableService.getPasswordMinLength() + " characters long.");
			}
			return false;
		}
		if (applicationVariableService.getPasswordEnforceSpecialChar() && password.matches("^[a-zA-Z0-9]*$")) {
			if (throwException) {
				throw new InvalidPasswordException("Password must contain at least one special (non-alphanumeric) character.");
			}
			return false;
		}
		return true;
	}
	
	private boolean validateEmail(String email, boolean throwException) {
		
		// TODO Make email a required field (?)
		if (email == null) {
			return false;
		}
		
		// TODO Implement this using regex (?)
		int atCharIndex = email.indexOf('@');
		int dotCharIndex = email.lastIndexOf('.');
		if (atCharIndex < 0 || dotCharIndex < 0 || atCharIndex > dotCharIndex) {
			if (throwException) {
				throw new InvalidEmailException("Invalid email address.");
			}
			return false;
		}
		return true;
	}
	
	/** Assumes username is not null. */
	private boolean checkIfUsernameExists(String username, Integer ignoreId, boolean throwException) {
		if (!username.equalsIgnoreCase(ApplicationConstant.SUPER_ADMIN_USERNAME)) {
			User existing = userDao.getUserByUsername(username);
			if (existing == null || existing.getId().equals(ignoreId)) {
				return false;
			}
		}
		if (throwException) {
			throw new AlreadyExistsException("Username '" + username + "' already exists.");
		}
		return true;
	}
	
}
