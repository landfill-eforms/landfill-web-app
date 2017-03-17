package org.lacitysan.landfill.server.service;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.lacitysan.landfill.server.exception.user.InvalidPasswordException;
import org.lacitysan.landfill.server.exception.user.InvalidUsernameException;
import org.lacitysan.landfill.server.persistence.dao.user.UserDao;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	public User create(User user) {
		
		// Check if username is valid.
		validateUsername(user.getUsername(), true);
		
		// Check if password is valid.
		validatePassword(user.getPassword(), true);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		user.setEnabled(true);
		
		userDao.create(user);
		
		return user;
		
	}
	
	/**
	 * Check username against username restrictions as imposed by the application properties.
	 * @param username The username to be checked.
	 * @param throwException Whether to throw an exception if the username is not valid.
	 * @return True if the username is valid, false otherwise.
	 */
	public boolean validateUsername(String username, boolean throwException) {
		if (username.length() < ApplicationProperty.MINIMUM_USERNAME_LENGTH) {
			if (throwException) {
				throw new InvalidUsernameException("Username must be at least " + ApplicationProperty.MINIMUM_USERNAME_LENGTH + " characters long.");
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
	public boolean validatePassword(String password, boolean throwException) {
		if (password.length() < ApplicationProperty.MINIMUM_PASSWORD_LENGTH) {
			if (throwException) {
				throw new InvalidPasswordException("Password must be at least " + ApplicationProperty.MINIMUM_PASSWORD_LENGTH + " characters long.");
			}
			return false;
		}
		// TODO Add ability to check if password contains special characters.
		return true;
	}
	
}
