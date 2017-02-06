package org.lacitysan.landfill.server.model;

/**
 * @author Alvin Quach
 */
public enum UserRole {
	
	SUPER_ADMIN				("Super Admin", "Super Admin"),
	ADMIN					("Admin", "Admin"),
	CREATE_USERS			("Create Users", UserRoleGroup.USERS, "Create", "User can create new users."),
	DEACTIVATE_USERS		("Deactivate Users", UserRoleGroup.USERS, "Deactivate", "User can deactive other non-admin users."),
	RESET_USER_PASSWORDS	("Reset User Passwords", UserRoleGroup.USERS, "Reset Password", "User can reset the passwords of other non-admin users."),
	RESET_USER_USERNAMES	("Reset User Usernames", UserRoleGroup.USERS, "Reset Username", "User can reset the usernames of other non-admin users."),
	EDIT_USER_PROFILES		("Edit User Profiles", UserRoleGroup.USERS, "Edit Profile", "User can edit the profiles of other non-admin users."),
	ASSIGN_EMPLOYEE_ID		("Assign Employee ID", UserRoleGroup.USERS, "Assign Employee ID", "User can change the employee ID associated with non-admin user accounts."),
	ASSIGN_USER_GROUPS		("Assign User Groups", UserRoleGroup.USERS, "Assign Groups", "User can assign user groups to non-admin users."),
	CREATE_USER_GROUPS		("Create User Groups", UserRoleGroup.USER_GROUPS, "Create", "User can create new user groups."),
	DELETE_USER_GROUPS 		("Delete User Groups", UserRoleGroup.USER_GROUPS, "Delete", "User can delete existing user groups."),
	EDIT_USER_GROUPS		("Edit User Groups", UserRoleGroup.USER_GROUPS, "Edit", "User can add/remove roles and rename existing user groups."),
	TEST					("Test", "Testing 123");
	
	private String name;
	private String group;
	private String groupAction;
	private String description;
	
	private UserRole(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	private UserRole(String name, String group, String groupAction, String description) {
		this.name = name;
		this.group = group;
		this.groupAction = groupAction;
		this.description = description;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getGroup() {
		return group;
	}

	public String getGroupAction() {
		return groupAction;
	}

	/** Optional grouping names for user roles. */
	private static class UserRoleGroup {
		protected final static String USERS = "Users";
		protected final static String USER_GROUPS = "User Groups";
	}
	
}
