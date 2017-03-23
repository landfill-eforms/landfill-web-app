/**
 * This enum was automatically generated from UserPermission.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UserPermission {

	static readonly ADMIN:UserPermission = new UserPermission(1, "ADMIN", "Admin", null, null, "Admin");
	static readonly CREATE_USERS:UserPermission = new UserPermission(2, "CREATE_USERS", "Create Users", "Users", "Create", "User can create new users.");
	static readonly DEACTIVATE_USERS:UserPermission = new UserPermission(3, "DEACTIVATE_USERS", "Deactivate Users", "Users", "Deactivate", "User can deactive other non-admin users.");
	static readonly RESET_USER_PASSWORDS:UserPermission = new UserPermission(4, "RESET_USER_PASSWORDS", "Reset User Passwords", "Users", "Reset Password", "User can reset the passwords of other non-admin users.");
	static readonly RESET_USER_USERNAMES:UserPermission = new UserPermission(5, "RESET_USER_USERNAMES", "Reset User Usernames", "Users", "Reset Username", "User can reset the usernames of other non-admin users.");
	static readonly EDIT_USER_PROFILES:UserPermission = new UserPermission(6, "EDIT_USER_PROFILES", "Edit User Profiles", "Users", "Edit Profile", "User can edit the profiles of other non-admin users.");
	static readonly ASSIGN_EMPLOYEE_ID:UserPermission = new UserPermission(7, "ASSIGN_EMPLOYEE_ID", "Assign Employee ID", "Users", "Assign Employee ID", "User can change the employee ID associated with non-admin user accounts.");
	static readonly ASSIGN_USER_GROUPS:UserPermission = new UserPermission(8, "ASSIGN_USER_GROUPS", "Assign User Groups", "Users", "Assign Groups", "User can assign user groups to non-admin users.");
	static readonly CREATE_USER_GROUPS:UserPermission = new UserPermission(9, "CREATE_USER_GROUPS", "Create User Groups", "User Groups", "Create", "User can create new user groups.");
	static readonly DELETE_USER_GROUPS:UserPermission = new UserPermission(10, "DELETE_USER_GROUPS", "Delete User Groups", "User Groups", "Delete", "User can delete existing user groups.");
	static readonly EDIT_USER_GROUPS:UserPermission = new UserPermission(11, "EDIT_USER_GROUPS", "Edit User Groups", "User Groups", "Edit", "User can add/remove permissions and rename existing user groups.");
	static readonly UPLOAD_MOBILE_DATA:UserPermission = new UserPermission(12, "UPLOAD_MOBILE_DATA", "Upload Mobile Data", "Mobile App Sync", "Upload", "User can upload data form the mobile application.");
	static readonly DOWNLOAD_MOBILE_DATA:UserPermission = new UserPermission(13, "DOWNLOAD_MOBILE_DATA", "Download Mobile Data", "Mobile App Sync", "Download", "User can download data for transfer to the mobile applications.");
	static readonly VIEW_UNVERIFIED_DATA_SET_LIST:UserPermission = new UserPermission(14, "VIEW_UNVERIFIED_DATA_SET_LIST", "View Unverified Data Set List", "Unverified Data", "View List", "User can view the list of unverified data sets.");
	static readonly VIEW_UNVERIFIED_DATA_SET:UserPermission = new UserPermission(15, "VIEW_UNVERIFIED_DATA_SET", "View Unverified Data Set", "Unverified Data", "View List", "User can view the details of an unverified data set.");
	static readonly EDIT_UNVERIFIED_DATA_SET:UserPermission = new UserPermission(16, "EDIT_UNVERIFIED_DATA_SET", "Edit Unverified Data Set", "Unverified Data", "View List", "User can edit and save the details of an unverified data set.");
	static readonly DELETE_UNVERIFIED_DATA_SET:UserPermission = new UserPermission(17, "DELETE_UNVERIFIED_DATA_SET", "Delete Unverified Data Set", "Unverified Data", "View List", "User can delete entire unverified data sets.");
	static readonly DELETE_UNVERIFIED_DATA:UserPermission = new UserPermission(18, "DELETE_UNVERIFIED_DATA", "Delete Unverified Data", "Unverified Data", "View List", "User can delete individual data entries in an unverified data set.");
	static readonly COMMIT_UNVERIFIED_DATA_SET:UserPermission = new UserPermission(19, "COMMIT_UNVERIFIED_DATA_SET", "Commit Unverified Data Set", "Unverified Data", "View List", "User can commit unverified data sets.");
	static readonly GENERATE_REPORTS:UserPermission = new UserPermission(20, "GENERATE_REPORTS", "Generate Reports", "Reports", "Generate", "User can generate reports.");

	readonly ordinal:number;
	readonly constantName:string;
	readonly name:string;
	readonly category:string;
	readonly categoryAction:string;
	readonly description:string;

	private constructor(ordinal:number, constantName:string, name:string, category:string, categoryAction:string, description:string) {
		this.ordinal = ordinal;
		this.constantName = constantName;
		this.name = name;
		this.category = category;
		this.categoryAction = categoryAction;
		this.description = description;
	}

	static values():UserPermission[] {
		return [
			UserPermission.ADMIN,
			UserPermission.CREATE_USERS,
			UserPermission.DEACTIVATE_USERS,
			UserPermission.RESET_USER_PASSWORDS,
			UserPermission.RESET_USER_USERNAMES,
			UserPermission.EDIT_USER_PROFILES,
			UserPermission.ASSIGN_EMPLOYEE_ID,
			UserPermission.ASSIGN_USER_GROUPS,
			UserPermission.CREATE_USER_GROUPS,
			UserPermission.DELETE_USER_GROUPS,
			UserPermission.EDIT_USER_GROUPS,
			UserPermission.UPLOAD_MOBILE_DATA,
			UserPermission.DOWNLOAD_MOBILE_DATA,
			UserPermission.VIEW_UNVERIFIED_DATA_SET_LIST,
			UserPermission.VIEW_UNVERIFIED_DATA_SET,
			UserPermission.EDIT_UNVERIFIED_DATA_SET,
			UserPermission.DELETE_UNVERIFIED_DATA_SET,
			UserPermission.DELETE_UNVERIFIED_DATA,
			UserPermission.COMMIT_UNVERIFIED_DATA_SET,
			UserPermission.GENERATE_REPORTS
		];
	}

}