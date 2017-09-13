package org.lacitysan.landfill.server.service.user;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.config.app.vars.ApplicationVariableService;
import org.lacitysan.landfill.server.exception.AlreadyExistsException;
import org.lacitysan.landfill.server.exception.UnknownEntityException;
import org.lacitysan.landfill.server.exception.user.InvalidEmailException;
import org.lacitysan.landfill.server.exception.user.InvalidPasswordException;
import org.lacitysan.landfill.server.exception.user.InvalidUsernameException;
import org.lacitysan.landfill.server.exception.user.UnauthorizedUserException;
import org.lacitysan.landfill.server.exception.user.UserGroupAssignmentException;
import org.lacitysan.landfill.server.persistence.dao.user.UserDao;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.lacitysan.landfill.server.persistence.entity.user.UserGroup;
import org.lacitysan.landfill.server.persistence.enums.user.UserPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Handles the logical operations for <code>User</code> entities.
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
	
	@Autowired
	UserGroupService userGroupService;
	
	/** 
	 * Gets the current user based on authentication stored in the security context holder.
	 * Super admin account will return null.
	 * @return
	 */
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			throw new UnauthorizedUserException("Current user authentication is invalid");
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
	
	/**
	 * Returns a list of users who are part of at least one inspector user group.
	 * The {@code User} objects returned will have some unnecessary fields
	 * nullified to reduce the amount of data that needs to be queried.
	 * The fields that are included are sufficient for identifying each user.
	 * @return A list of users who are part of at least one inspector user group.
	 */
	public List<User> getAllInspectors() {
		return this.userGroupService.getAllInspectorGroups().stream()
				.flatMap(userGroup -> userGroup.getUsers().stream())
				.map(user -> {
					// Nullify user groups to get around Hibernate lazy load issue
					// without having to actually fetch the user groups.
					user.setUserGroups(null);
					return user;
				})
				.collect(Collectors.toList());
	}
	
	public User create(User user) {
		
		// Replace any null values with the appropriate values.
		intializeNullFields(user);
		
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
			throw new UnknownEntityException("Attempting to update unknown user.");
		}
		
		// Only super admin and admin can edit other admin accounts.
		if (userGroupService.contains(existing.getUserGroups(), UserPermission.ADMIN)) {
			User currentUser = getCurrentUser();
			if (currentUser != null && !userGroupService.contains(currentUser.getUserGroups(), UserPermission.ADMIN)) {
				throw new UnauthorizedUserException("You are not authorized to change this account's username.");
			}
		}
		
		existing.setUsername(user.getUsername());
		
		return userDao.update(existing);
		
	}
	
	public User changePassword(User user) {
		User existing = userDao.getById(user.getId());
		if (existing == null) {
			throw new UnknownEntityException("Attempting to update unknown user.");
		}
		if (userGroupService.contains(existing.getUserGroups(), UserPermission.ADMIN)) {
			User currentUser = getCurrentUser();
			if (currentUser != null && !userGroupService.contains(currentUser.getUserGroups(), UserPermission.ADMIN)) {
				throw new UnauthorizedUserException("You are not authorized to change this account's password.");
			}
		}
		validatePassword(user.getPassword(), true);
		existing.setPassword(passwordEncoder.encode(user.getPassword()));
		return userDao.update(existing);
	}
	
	public User updateProfile(User user) {
		intializeNullFields(user);
		User existing = userDao.getById(user.getId());
		if (existing == null) {
			throw new UnknownEntityException("Attempting to update unknown user.");
		}
		if (userGroupService.contains(existing.getUserGroups(), UserPermission.ADMIN)) {
			User currentUser = getCurrentUser();
			if (currentUser != null && !userGroupService.contains(currentUser.getUserGroups(), UserPermission.ADMIN)) {
				throw new UnauthorizedUserException("You are not authorized to edit this user's profile.");
			}
		}
		existing.setFirstname(user.getFirstname());
		existing.setMiddlename(user.getMiddlename());
		existing.setLastname(user.getLastname());
		existing.setEmployeeId(user.getEmployeeId()); // TODO Add validation for employee ID.
		if (validateUserGroups(existing.getUserGroups(), user.getUserGroups(), true)) {
			existing.setUserGroups(user.getUserGroups());
		}
		if (validateEmail(user.getEmailAddress(), true)) {
			existing.setEmailAddress(user.getEmailAddress());
		}
		return userDao.update(existing);
	}
	
	public User updateStatus(User user) {
		User existing = userDao.getById(user.getId());
		if (existing == null) {
			throw new UnknownEntityException("Attempting to update unknown user.");
		}
		if (userGroupService.contains(existing.getUserGroups(), UserPermission.ADMIN)) {
			User currentUser = getCurrentUser();
			if (currentUser != null && !userGroupService.contains(currentUser.getUserGroups(), UserPermission.ADMIN)) {
				throw new UnauthorizedUserException("You are not authorized to change this account's status.");
			}
		}
		existing.setEnabled(user.getEnabled());
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
	
	/**
	 * Checks whether the user is already belongs to the admin user group.
	 * If they do, then any groups can be added to the user.
	 * If they don't, then groups containing the admin permission cannot be added to the user,
	 * unless group(s) are being added by the super admin.
	 * Likewise, if the user is already an admin, then his admin status can only be removed by the super admin.
	 * <br></br>
	 * Ideally, there should be only one user group with the admin permission.
	 * @param existing
	 * @param updated
	 * @return
	 */
	private boolean validateUserGroups(Set<UserGroup> existing, Set<UserGroup> updated, boolean throwException) {
		if (getCurrentUser() == null) {
			return true; // null = super admin
		}
		boolean result = !(userGroupService.contains(existing, UserPermission.ADMIN) ^ userGroupService.contains(updated, UserPermission.ADMIN));
		if (!result && throwException) {
			throw new UserGroupAssignmentException("An illegal change the user's admin status was attempted.");
		}
		return result;
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
	
	/** Replaces null primitive wrapper fields with the appropriate non-null value. */
	private void intializeNullFields(User user) {
		if (user.getUsername() == null) {
			user.setUsername("");
		}
		if (user.getPassword() == null) {
			user.setPassword("");
		}
		if (user.getFirstname() == null) {
			user.setFirstname("");
		}
		if (user.getMiddlename() == null) {
			user.setMiddlename("");
		}
		if (user.getLastname() == null) {
			user.setLastname("");
		}
		if (user.getEmailAddress() == null) {
			user.setEmailAddress("");
		}
		if (user.getEmployeeId() == null) {
			user.setEmployeeId("");
		}
		if (user.getEnabled() == null) {
			user.setEnabled(false);
		}
	}
	
}
