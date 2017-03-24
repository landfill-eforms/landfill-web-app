package org.lacitysan.landfill.server.persistence.enums;

import org.lacitysan.landfill.server.json.LandfillEnumDeserializer;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @author Alvin Quach
 */
public enum UserPermission {
	
//	SUPER_ADMIN						("Super Admin", "Super Admin"),
	ADMIN							("Admin", "Admin"),
	VIEW_USER_LIST					("View User List", UserPermissionCategory.USERS, "View List", "User can view the list of users."),
	CREATE_USERS					("Create Users", UserPermissionCategory.USERS, "Create", "User can create new users."),
	DEACTIVATE_USERS				("Deactivate Users", UserPermissionCategory.USERS, "Deactivate", "User can deactive other non-admin users."),
	RESET_USER_PASSWORDS			("Reset User Passwords", UserPermissionCategory.USERS, "Reset Password", "User can reset the passwords of other non-admin users."),
	RESET_USER_USERNAMES			("Reset User Usernames", UserPermissionCategory.USERS, "Reset Username", "User can reset the usernames of other non-admin users."),
	EDIT_USER_PROFILES				("Edit User Profiles", UserPermissionCategory.USERS, "Edit Profile", "User can edit the profiles of other non-admin users."),
	ASSIGN_EMPLOYEE_ID				("Assign Employee ID", UserPermissionCategory.USERS, "Assign Employee ID", "User can change the employee ID associated with non-admin user accounts."),
	ASSIGN_USER_GROUPS				("Assign User Groups", UserPermissionCategory.USERS, "Assign Groups", "User can assign user groups to non-admin users."),
	VIEW_USER_GROUP_LIST			("View User Group List", UserPermissionCategory.USER_GROUPS, "View List", "User can view the list of user groups."),
	CREATE_USER_GROUPS				("Create User Groups", UserPermissionCategory.USER_GROUPS, "Create", "User can create new user groups."),
	DELETE_USER_GROUPS 				("Delete User Groups", UserPermissionCategory.USER_GROUPS, "Delete", "User can delete existing user groups."),
	EDIT_USER_GROUPS				("Edit User Groups", UserPermissionCategory.USER_GROUPS, "Edit", "User can add/remove permissions and rename existing user groups."),
	VIEW_INSTRUMENT_LIST			("View Instrument List", UserPermissionCategory.EQUIPMENT, "View List", "User can view the list of equipment."),
	CREATE_INSTRUMENTS				("Create Instrument", UserPermissionCategory.EQUIPMENT, "Create", "User can add new equipment entries."),
	EDIT_INSTRUMENTS				("Edit Instrument", UserPermissionCategory.EQUIPMENT, "Edit", "User can edit existing equipment entries."),
	VIEW_INSTRUMENT_TYPE_LIST		("View Instrument Type List", UserPermissionCategory.EQUIPMENT_TYPE, "View List", "User can view the list of equipment types."),
	CREATE_INSTRUMENT_TYPES			("Create Instrument Types", UserPermissionCategory.EQUIPMENT_TYPE, "Create", "User can add new equipment types."),
	EDIT_INSTRUMENT_TYPES			("Edit Instrument Types", UserPermissionCategory.EQUIPMENT_TYPE, "Edit", "User can edit existing equipment types."),
	DELETE_INSTRUMENT_TYPES			("Delete Instrument Types", UserPermissionCategory.EQUIPMENT_TYPE, "Delete", "User can delete existing equipment types."),
	UPLOAD_MOBILE_DATA				("Upload Mobile Data", UserPermissionCategory.MOBILE_APP_SYNC, "Upload", "User can upload data form the mobile application."),
	DOWNLOAD_MOBILE_DATA			("Download Mobile Data", UserPermissionCategory.MOBILE_APP_SYNC, "Download", "User can download data for transfer to the mobile applications."),
	VIEW_UNVERIFIED_DATA_SET_LIST	("View Unverified Data Set List", UserPermissionCategory.UNVERIFIED_DATA, "View List", "User can view the list of unverified data sets."),
	VIEW_UNVERIFIED_DATA_SET		("View Unverified Data Set", UserPermissionCategory.UNVERIFIED_DATA, "View List", "User can view the details of an unverified data set."),
	EDIT_UNVERIFIED_DATA_SET		("Edit Unverified Data Set", UserPermissionCategory.UNVERIFIED_DATA, "View List", "User can edit and save the details of an unverified data set."),
	DELETE_UNVERIFIED_DATA_SET		("Delete Unverified Data Set", UserPermissionCategory.UNVERIFIED_DATA, "View List", "User can delete entire unverified data sets."),
	DELETE_UNVERIFIED_DATA			("Delete Unverified Data", UserPermissionCategory.UNVERIFIED_DATA, "View List", "User can delete individual data entries in an unverified data set."),
	COMMIT_UNVERIFIED_DATA_SET		("Commit Unverified Data Set", UserPermissionCategory.UNVERIFIED_DATA, "View List", "User can commit unverified data sets."),
	GENERATE_REPORTS				("Generate Reports", UserPermissionCategory.REPORTS, "Generate", "User can generate reports.");
	
	private String name;
	private String category;
	private String categoryAction;
	private String description;
	
	private UserPermission(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	private UserPermission(String name, String category, String categoryAction, String description) {
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
	public static UserPermission deserialize(Object object) {
		return LandfillEnumDeserializer.deserialize(UserPermission.class, object);
	}

	/** Optional category groups for user permissions. */
	private static class UserPermissionCategory {
		protected final static String USERS = "Users";
		protected final static String USER_GROUPS = "User Groups";
		protected final static String EQUIPMENT = "Equipment";
		protected final static String EQUIPMENT_TYPE = "Equipment Type";
		protected final static String MOBILE_APP_SYNC = "Mobile App Sync";
		protected final static String UNVERIFIED_DATA = "Unverified Data";
		protected final static String REPORTS = "Reports";
	}
	
}
