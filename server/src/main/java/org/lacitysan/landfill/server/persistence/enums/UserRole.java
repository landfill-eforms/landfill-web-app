package org.lacitysan.landfill.server.persistence.enums;

import org.lacitysan.landfill.server.json.LandfillEnumDeserializer;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @author Alvin Quach
 */
public enum UserRole {
	
//	SUPER_ADMIN						("Super Admin", "Super Admin"),
	ADMIN							("Admin", "Admin"),
	CREATE_USERS					("Create Users", UserRoleCategory.USERS, "Create", "User can create new users."),
	DEACTIVATE_USERS				("Deactivate Users", UserRoleCategory.USERS, "Deactivate", "User can deactive other non-admin users."),
	RESET_USER_PASSWORDS			("Reset User Passwords", UserRoleCategory.USERS, "Reset Password", "User can reset the passwords of other non-admin users."),
	RESET_USER_USERNAMES			("Reset User Usernames", UserRoleCategory.USERS, "Reset Username", "User can reset the usernames of other non-admin users."),
	EDIT_USER_PROFILES				("Edit User Profiles", UserRoleCategory.USERS, "Edit Profile", "User can edit the profiles of other non-admin users."),
	ASSIGN_EMPLOYEE_ID				("Assign Employee ID", UserRoleCategory.USERS, "Assign Employee ID", "User can change the employee ID associated with non-admin user accounts."),
	ASSIGN_USER_GROUPS				("Assign User Groups", UserRoleCategory.USERS, "Assign Groups", "User can assign user groups to non-admin users."),
	CREATE_USER_GROUPS				("Create User Groups", UserRoleCategory.USER_GROUPS, "Create", "User can create new user groups."),
	DELETE_USER_GROUPS 				("Delete User Groups", UserRoleCategory.USER_GROUPS, "Delete", "User can delete existing user groups."),
	EDIT_USER_GROUPS				("Edit User Groups", UserRoleCategory.USER_GROUPS, "Edit", "User can add/remove roles and rename existing user groups."),
	UPLOAD_MOBILE_DATA				("Upload Mobile Data", UserRoleCategory.MOBILE_APP_SYNC, "Upload", "User can upload data form the mobile application."),
	DOWNLOAD_MOBILE_DATA			("Download Mobile Data", UserRoleCategory.MOBILE_APP_SYNC, "Download", "User can download data for transfer to the mobile applications."),
	VIEW_UNVERIFIED_DATA_SET_LIST	("View Unverified Data Set List", UserRoleCategory.UNVERIFIED_DATA, "View List", "User can view the list of unverified data sets."),
	VIEW_UNVERIFIED_DATA_SET		("View Unverified Data Set", UserRoleCategory.UNVERIFIED_DATA, "View List", "User can view the details of an unverified data set."),
	EDIT_UNVERIFIED_DATA_SET		("Edit Unverified Data Set", UserRoleCategory.UNVERIFIED_DATA, "View List", "User can edit and save the details of an unverified data set."),
	DELETE_UNVERIFIED_DATA_SET		("Delete Unverified Data Set", UserRoleCategory.UNVERIFIED_DATA, "View List", "User can delete entire unverified data sets."),
	DELETE_UNVERIFIED_DATA			("Delete Unverified Data", UserRoleCategory.UNVERIFIED_DATA, "View List", "User can delete individual data entries in an unverified data set."),
	COMMIT_UNVERIFIED_DATA_SET		("Commit Unverified Data Set", UserRoleCategory.UNVERIFIED_DATA, "View List", "User can commit unverified data sets."),
	GENERATE_REPORTS				("Generate Reports", UserRoleCategory.REPORTS, "Generate", "User can generate reports.");
	
	private String name;
	private String category;
	private String categoryAction;
	private String description;
	
	private UserRole(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	private UserRole(String name, String category, String categoryAction, String description) {
		this.name = name;
		this.category = category;
		this.categoryAction = categoryAction;
		this.description = description;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getCategory() {
		return category;
	}

	public String getCategoryAction() {
		return categoryAction;
	}
	
	@JsonCreator
	public static UserRole deserialize(Object object) {
		return LandfillEnumDeserializer.deserialize(UserRole.class, object);
	}

	/** Optional category groups for user roles. */
	private static class UserRoleCategory {
		protected final static String USERS = "Users";
		protected final static String USER_GROUPS = "User Groups";
		protected final static String MOBILE_APP_SYNC = "Mobile App Sync";
		protected final static String UNVERIFIED_DATA = "Unverified Data";
		protected final static String REPORTS = "Reports";
	}
	
}
