/**
 * This enum was automatically generated from UserPermission.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UserPermission {

	static readonly ADMIN:UserPermission = new UserPermission(0, "ADMIN", "Admin", null, null, "Admin");
	static readonly VIEW_USER:UserPermission = new UserPermission(1, "VIEW_USER", "User can view the list of users.", "Users", "View List", "User can view the list of users.");
	static readonly CREATE_USERS:UserPermission = new UserPermission(2, "CREATE_USERS", "User can create new users.", "Users", "Create", "User can create new users.");
	static readonly DEACTIVATE_USERS:UserPermission = new UserPermission(3, "DEACTIVATE_USERS", "User can deactivate other non-admin user accounts.", "Users", "Deactivate", "User can deactivate other non-admin user accounts.");
	static readonly RESET_USER_PASSWORDS:UserPermission = new UserPermission(4, "RESET_USER_PASSWORDS", "User can reset the passwords of other non-admin users.", "Users", "Reset Password", "User can reset the passwords of other non-admin users.");
	static readonly RESET_USER_USERNAMES:UserPermission = new UserPermission(5, "RESET_USER_USERNAMES", "User can reset the usernames of other non-admin users.", "Users", "Reset Username", "User can reset the usernames of other non-admin users.");
	static readonly EDIT_USER_PROFILES:UserPermission = new UserPermission(6, "EDIT_USER_PROFILES", "User can edit the profiles of other non-admin users.", "Users", "Edit Profile", "User can edit the profiles of other non-admin users.");
	static readonly ASSIGN_EMPLOYEE_ID:UserPermission = new UserPermission(7, "ASSIGN_EMPLOYEE_ID", "User can change the employee ID associated with non-admin user accounts.", "Users", "Assign Employee ID", "User can change the employee ID associated with non-admin user accounts.");
	static readonly ASSIGN_USER_GROUPS:UserPermission = new UserPermission(8, "ASSIGN_USER_GROUPS", "User can assign user groups to non-admin users.", "Users", "Assign Groups", "User can assign user groups to non-admin users.");
	static readonly VIEW_USER_GROUP_LIST:UserPermission = new UserPermission(9, "VIEW_USER_GROUP_LIST", "User can view the list of user groups.", "User Groups", "View List", "User can view the list of user groups.");
	static readonly CREATE_USER_GROUPS:UserPermission = new UserPermission(10, "CREATE_USER_GROUPS", "User can create new user groups.", "User Groups", "Create", "User can create new user groups.");
	static readonly DELETE_USER_GROUPS:UserPermission = new UserPermission(11, "DELETE_USER_GROUPS", "User can delete existing user groups.", "User Groups", "Delete", "User can delete existing user groups.");
	static readonly EDIT_USER_GROUPS:UserPermission = new UserPermission(12, "EDIT_USER_GROUPS", "User can add/remove roles and rename existing user groups.", "User Groups", "Edit", "User can add/remove roles and rename existing user groups.");
	static readonly VIEW_INSTRUMENT_LIST:UserPermission = new UserPermission(13, "VIEW_INSTRUMENT_LIST", "User can view the list of equipment.", "Equipment", "View List", "User can view the list of equipment.");
	static readonly CREATE_INSTRUMENTS:UserPermission = new UserPermission(14, "CREATE_INSTRUMENTS", "User can add new equipment entries.", "Equipment", "Create", "User can add new equipment entries.");
	static readonly EDIT_INSTRUMENTS:UserPermission = new UserPermission(15, "EDIT_INSTRUMENTS", "User can edit existing equipment entries.", "Equipment", "Edit", "User can edit existing equipment entries.");
	static readonly VIEW_INSTRUMENT_TYPE_LIST:UserPermission = new UserPermission(16, "VIEW_INSTRUMENT_TYPE_LIST", "User can view the list of equipment types.", "Equipment Type", "View List", "User can view the list of equipment types.");
	static readonly CREATE_INSTRUMENT_TYPES:UserPermission = new UserPermission(17, "CREATE_INSTRUMENT_TYPES", "User can add new equipment types.", "Equipment Type", "Create", "User can add new equipment types.");
	static readonly EDIT_INSTRUMENT_TYPES:UserPermission = new UserPermission(18, "EDIT_INSTRUMENT_TYPES", "User can edit existing equipment types.", "Equipment Type", "Edit", "User can edit existing equipment types.");
	static readonly DELETE_INSTRUMENT_TYPES:UserPermission = new UserPermission(19, "DELETE_INSTRUMENT_TYPES", "User can delete existing equipment types.", "Equipment Type", "Delete", "User can delete existing equipment types.");
	static readonly UPLOAD_MOBILE_DATA:UserPermission = new UserPermission(20, "UPLOAD_MOBILE_DATA", "User can upload data form the mobile application.", "Mobile App Sync", "Upload", "User can upload data form the mobile application.");
	static readonly DOWNLOAD_MOBILE_DATA:UserPermission = new UserPermission(21, "DOWNLOAD_MOBILE_DATA", "User can download data for transfer to the mobile applications.", "Mobile App Sync", "Download", "User can download data for transfer to the mobile applications.");
	static readonly VIEW_UNVERIFIED_DATA_SET_LIST:UserPermission = new UserPermission(22, "VIEW_UNVERIFIED_DATA_SET_LIST", "User can view the list of unverified data sets.", "Unverified Data", "View List", "User can view the list of unverified data sets.");
	static readonly VIEW_UNVERIFIED_DATA_SET:UserPermission = new UserPermission(23, "VIEW_UNVERIFIED_DATA_SET", "User can view the details of an unverified data set.", "Unverified Data", "View Sets", "User can view the details of an unverified data set.");
	static readonly EDIT_UNVERIFIED_DATA_SET:UserPermission = new UserPermission(24, "EDIT_UNVERIFIED_DATA_SET", "User can edit and save the details of an unverified data set.", "Unverified Data", "Edit Sets", "User can edit and save the details of an unverified data set.");
	static readonly DELETE_UNVERIFIED_DATA_SET:UserPermission = new UserPermission(25, "DELETE_UNVERIFIED_DATA_SET", "User can delete entire unverified data sets.", "Unverified Data", "Delete Sets", "User can delete entire unverified data sets.");
	static readonly DELETE_UNVERIFIED_DATA:UserPermission = new UserPermission(26, "DELETE_UNVERIFIED_DATA", "User can delete individual data entries in an unverified data set.", "Unverified Data", "Delete Data", "User can delete individual data entries in an unverified data set.");
	static readonly COMMIT_UNVERIFIED_DATA_SET:UserPermission = new UserPermission(27, "COMMIT_UNVERIFIED_DATA_SET", "User can commit unverified data sets.", "Unverified Data", "Commit Set", "User can commit unverified data sets.");
	static readonly GENERATE_REPORTS:UserPermission = new UserPermission(28, "GENERATE_REPORTS", "User can generate reports.", "Reports", "Generate", "User can generate reports.");
	static readonly EMAIL_GENERATED_REPORT:UserPermission = new UserPermission(29, "EMAIL_GENERATED_REPORT", "", "Reports", "", "");
	static readonly VIEW_VERIFIED_DATA:UserPermission = new UserPermission(30, "VIEW_VERIFIED_DATA", "User can view list of verified data", "Web App", "View List", "User can view list of verified data");
	static readonly QUERY_VERIFIED_DATA_SET:UserPermission = new UserPermission(31, "QUERY_VERIFIED_DATA_SET", "User can find data by specific fields", "Web App", "Query List", "User can find data by specific fields");

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
			UserPermission.VIEW_USER,
			UserPermission.CREATE_USERS,
			UserPermission.DEACTIVATE_USERS,
			UserPermission.RESET_USER_PASSWORDS,
			UserPermission.RESET_USER_USERNAMES,
			UserPermission.EDIT_USER_PROFILES,
			UserPermission.ASSIGN_EMPLOYEE_ID,
			UserPermission.ASSIGN_USER_GROUPS,
			UserPermission.VIEW_USER_GROUP_LIST,
			UserPermission.CREATE_USER_GROUPS,
			UserPermission.DELETE_USER_GROUPS,
			UserPermission.EDIT_USER_GROUPS,
			UserPermission.VIEW_INSTRUMENT_LIST,
			UserPermission.CREATE_INSTRUMENTS,
			UserPermission.EDIT_INSTRUMENTS,
			UserPermission.VIEW_INSTRUMENT_TYPE_LIST,
			UserPermission.CREATE_INSTRUMENT_TYPES,
			UserPermission.EDIT_INSTRUMENT_TYPES,
			UserPermission.DELETE_INSTRUMENT_TYPES,
			UserPermission.UPLOAD_MOBILE_DATA,
			UserPermission.DOWNLOAD_MOBILE_DATA,
			UserPermission.VIEW_UNVERIFIED_DATA_SET_LIST,
			UserPermission.VIEW_UNVERIFIED_DATA_SET,
			UserPermission.EDIT_UNVERIFIED_DATA_SET,
			UserPermission.DELETE_UNVERIFIED_DATA_SET,
			UserPermission.DELETE_UNVERIFIED_DATA,
			UserPermission.COMMIT_UNVERIFIED_DATA_SET,
			UserPermission.GENERATE_REPORTS,
			UserPermission.EMAIL_GENERATED_REPORT,
			UserPermission.VIEW_VERIFIED_DATA,
			UserPermission.QUERY_VERIFIED_DATA_SET
		];
	}

}

/** Temporary fix for an issue with angular-cli's Ahead-of-Time (AoT) compilation. */
export const AOTUserPermission = {
	ADMIN: UserPermission["ADMIN"],
	VIEW_USER: UserPermission["VIEW_USER"],
	CREATE_USERS: UserPermission["CREATE_USERS"],
	DEACTIVATE_USERS: UserPermission["DEACTIVATE_USERS"],
	RESET_USER_PASSWORDS: UserPermission["RESET_USER_PASSWORDS"],
	RESET_USER_USERNAMES: UserPermission["RESET_USER_USERNAMES"],
	EDIT_USER_PROFILES: UserPermission["EDIT_USER_PROFILES"],
	ASSIGN_EMPLOYEE_ID: UserPermission["ASSIGN_EMPLOYEE_ID"],
	ASSIGN_USER_GROUPS: UserPermission["ASSIGN_USER_GROUPS"],
	VIEW_USER_GROUP_LIST: UserPermission["VIEW_USER_GROUP_LIST"],
	CREATE_USER_GROUPS: UserPermission["CREATE_USER_GROUPS"],
	DELETE_USER_GROUPS: UserPermission["DELETE_USER_GROUPS"],
	EDIT_USER_GROUPS: UserPermission["EDIT_USER_GROUPS"],
	VIEW_INSTRUMENT_LIST: UserPermission["VIEW_INSTRUMENT_LIST"],
	CREATE_INSTRUMENTS: UserPermission["CREATE_INSTRUMENTS"],
	EDIT_INSTRUMENTS: UserPermission["EDIT_INSTRUMENTS"],
	VIEW_INSTRUMENT_TYPE_LIST: UserPermission["VIEW_INSTRUMENT_TYPE_LIST"],
	CREATE_INSTRUMENT_TYPES: UserPermission["CREATE_INSTRUMENT_TYPES"],
	EDIT_INSTRUMENT_TYPES: UserPermission["EDIT_INSTRUMENT_TYPES"],
	DELETE_INSTRUMENT_TYPES: UserPermission["DELETE_INSTRUMENT_TYPES"],
	UPLOAD_MOBILE_DATA: UserPermission["UPLOAD_MOBILE_DATA"],
	DOWNLOAD_MOBILE_DATA: UserPermission["DOWNLOAD_MOBILE_DATA"],
	VIEW_UNVERIFIED_DATA_SET_LIST: UserPermission["VIEW_UNVERIFIED_DATA_SET_LIST"],
	VIEW_UNVERIFIED_DATA_SET: UserPermission["VIEW_UNVERIFIED_DATA_SET"],
	EDIT_UNVERIFIED_DATA_SET: UserPermission["EDIT_UNVERIFIED_DATA_SET"],
	DELETE_UNVERIFIED_DATA_SET: UserPermission["DELETE_UNVERIFIED_DATA_SET"],
	DELETE_UNVERIFIED_DATA: UserPermission["DELETE_UNVERIFIED_DATA"],
	COMMIT_UNVERIFIED_DATA_SET: UserPermission["COMMIT_UNVERIFIED_DATA_SET"],
	GENERATE_REPORTS: UserPermission["GENERATE_REPORTS"],
	EMAIL_GENERATED_REPORT: UserPermission["EMAIL_GENERATED_REPORT"],
	VIEW_VERIFIED_DATA: UserPermission["VIEW_VERIFIED_DATA"],
	QUERY_VERIFIED_DATA_SET: UserPermission["QUERY_VERIFIED_DATA_SET"]
}