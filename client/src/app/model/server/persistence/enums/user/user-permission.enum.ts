/**
 * This enum was automatically generated from UserPermission.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 * This enum includes a fix for the Ahead-of-Time (AoT) compile bug which prevents compilation if a constructor has to be called within a static variable from a different class.
 * As a result, this enum doesn't have a constructor, and its constants are declared directly instead of through a constructor.
 */
export class UserPermission {

	static readonly ADMIN:UserPermission = {
		ordinal: 0,
		constantName: "ADMIN",
		name: "Admin",
		category: null,
		categoryAction: null,
		description: "Admin"
	};

	static readonly VIEW_USERS:UserPermission = {
		ordinal: 1,
		constantName: "VIEW_USERS",
		name: "View Users",
		category: "Users",
		categoryAction: "View List",
		description: "User can view existing users."
	};

	static readonly CREATE_USERS:UserPermission = {
		ordinal: 2,
		constantName: "CREATE_USERS",
		name: "Create Users",
		category: "Users",
		categoryAction: "Create",
		description: "User can create new users."
	};

	static readonly CHANGE_USER_STATUS:UserPermission = {
		ordinal: 3,
		constantName: "CHANGE_USER_STATUS",
		name: "Change User Status",
		category: "Users",
		categoryAction: "Enable/Disable",
		description: "User can enable/disable other non-admin user accounts."
	};

	static readonly RESET_USER_PASSWORDS:UserPermission = {
		ordinal: 4,
		constantName: "RESET_USER_PASSWORDS",
		name: "Reset User Passwords",
		category: "Users",
		categoryAction: "Reset Password",
		description: "User can reset the passwords of non-admin users."
	};

	static readonly RESET_USER_USERNAMES:UserPermission = {
		ordinal: 5,
		constantName: "RESET_USER_USERNAMES",
		name: "Reset User Usernames",
		category: "Users",
		categoryAction: "Reset Username",
		description: "User can reset the usernames of non-admin users."
	};

	static readonly EDIT_USER_PROFILES:UserPermission = {
		ordinal: 6,
		constantName: "EDIT_USER_PROFILES",
		name: "Edit User Profiles",
		category: "Users",
		categoryAction: "Edit Profile",
		description: "User can edit the profiles of other non-admin users."
	};

	static readonly VIEW_USER_GROUPS:UserPermission = {
		ordinal: 7,
		constantName: "VIEW_USER_GROUPS",
		name: "View User Groups",
		category: "User Groups",
		categoryAction: "View List",
		description: "User can view existing user groups."
	};

	static readonly CREATE_USER_GROUPS:UserPermission = {
		ordinal: 8,
		constantName: "CREATE_USER_GROUPS",
		name: "Create User Groups",
		category: "User Groups",
		categoryAction: "Create",
		description: "User can create new user groups."
	};

	static readonly DELETE_USER_GROUPS:UserPermission = {
		ordinal: 9,
		constantName: "DELETE_USER_GROUPS",
		name: "Delete User Groups",
		category: "User Groups",
		categoryAction: "Delete",
		description: "User can delete existing user groups."
	};

	static readonly EDIT_USER_GROUPS:UserPermission = {
		ordinal: 10,
		constantName: "EDIT_USER_GROUPS",
		name: "Edit User Groups",
		category: "User Groups",
		categoryAction: "Edit",
		description: "User can add/remove roles and rename existing user groups."
	};

	static readonly VIEW_INSTRUMENTS:UserPermission = {
		ordinal: 11,
		constantName: "VIEW_INSTRUMENTS",
		name: "View Instruments",
		category: "Equipment",
		categoryAction: "View List",
		description: "User can view existing equipment."
	};

	static readonly CREATE_INSTRUMENTS:UserPermission = {
		ordinal: 12,
		constantName: "CREATE_INSTRUMENTS",
		name: "Create Instruments",
		category: "Equipment",
		categoryAction: "Create",
		description: "User can add new equipment entries."
	};

	static readonly EDIT_INSTRUMENTS:UserPermission = {
		ordinal: 13,
		constantName: "EDIT_INSTRUMENTS",
		name: "Edit Instruments",
		category: "Equipment",
		categoryAction: "Edit",
		description: "User can edit existing equipment entries."
	};

	static readonly DELETE_INSTRUMENTS:UserPermission = {
		ordinal: 14,
		constantName: "DELETE_INSTRUMENTS",
		name: "Delete Instruments",
		category: "Equipment",
		categoryAction: "Delete",
		description: "User can delete existing equipment entries."
	};

	static readonly VIEW_INSTRUMENT_TYPES:UserPermission = {
		ordinal: 15,
		constantName: "VIEW_INSTRUMENT_TYPES",
		name: "View Instrument Types",
		category: "Equipment Type",
		categoryAction: "View List",
		description: "User can view existing equipment types."
	};

	static readonly CREATE_INSTRUMENT_TYPES:UserPermission = {
		ordinal: 16,
		constantName: "CREATE_INSTRUMENT_TYPES",
		name: "Create Instrument Types",
		category: "Equipment Type",
		categoryAction: "Create",
		description: "User can add new equipment types."
	};

	static readonly EDIT_INSTRUMENT_TYPES:UserPermission = {
		ordinal: 17,
		constantName: "EDIT_INSTRUMENT_TYPES",
		name: "Edit Instrument Types",
		category: "Equipment Type",
		categoryAction: "Edit",
		description: "User can edit existing equipment types."
	};

	static readonly DELETE_INSTRUMENT_TYPES:UserPermission = {
		ordinal: 18,
		constantName: "DELETE_INSTRUMENT_TYPES",
		name: "Delete Instrument Types",
		category: "Equipment Type",
		categoryAction: "Delete",
		description: "User can delete existing equipment types."
	};

	static readonly UPLOAD_MOBILE_DATA:UserPermission = {
		ordinal: 19,
		constantName: "UPLOAD_MOBILE_DATA",
		name: "Upload Mobile Data",
		category: "Mobile App Sync",
		categoryAction: "Upload",
		description: "User can upload data form the mobile application."
	};

	static readonly DOWNLOAD_MOBILE_DATA:UserPermission = {
		ordinal: 20,
		constantName: "DOWNLOAD_MOBILE_DATA",
		name: "Download Mobile Data",
		category: "Mobile App Sync",
		categoryAction: "Download",
		description: "User can download data for transfer to the mobile applications."
	};

	static readonly VIEW_UNVERIFIED_DATA_SETS:UserPermission = {
		ordinal: 21,
		constantName: "VIEW_UNVERIFIED_DATA_SETS",
		name: "View Unverified Data Sets",
		category: "Unverified Data",
		categoryAction: "View List",
		description: "User can view existing unverified data sets."
	};

	static readonly VIEW_UNVERIFIED_DATA_SET:UserPermission = {
		ordinal: 22,
		constantName: "VIEW_UNVERIFIED_DATA_SET",
		name: "View Unverified Data Set",
		category: "Unverified Data",
		categoryAction: "View Sets",
		description: "User can view the details of an unverified data set."
	};

	static readonly EDIT_UNVERIFIED_DATA_SET:UserPermission = {
		ordinal: 23,
		constantName: "EDIT_UNVERIFIED_DATA_SET",
		name: "Edit Unverified Data Set",
		category: "Unverified Data",
		categoryAction: "Edit Sets",
		description: "User can edit and save the details of an unverified data set."
	};

	static readonly DELETE_UNVERIFIED_DATA_SET:UserPermission = {
		ordinal: 24,
		constantName: "DELETE_UNVERIFIED_DATA_SET",
		name: "Delete Unverified Data Set",
		category: "Unverified Data",
		categoryAction: "Delete Sets",
		description: "User can delete entire unverified data sets."
	};

	static readonly DELETE_UNVERIFIED_DATA:UserPermission = {
		ordinal: 25,
		constantName: "DELETE_UNVERIFIED_DATA",
		name: "Delete Unverified Data",
		category: "Unverified Data",
		categoryAction: "Delete Data",
		description: "User can delete individual data entries in an unverified data set."
	};

	static readonly COMMIT_UNVERIFIED_DATA_SET:UserPermission = {
		ordinal: 26,
		constantName: "COMMIT_UNVERIFIED_DATA_SET",
		name: "Commit Unverified Data Set",
		category: "Unverified Data",
		categoryAction: "Commit Set",
		description: "User can commit unverified data sets."
	};

	static readonly VIEW_EXCEEDANCES:UserPermission = {
		ordinal: 27,
		constantName: "VIEW_EXCEEDANCES",
		name: "View Exceedances",
		category: "Exceedances",
		categoryAction: "View",
		description: "User can view exceedances (IME, ISE, etc.)."
	};

	static readonly EDIT_EXCEEDANCES:UserPermission = {
		ordinal: 28,
		constantName: "EDIT_EXCEEDANCES",
		name: "Edit Exceedances",
		category: "Exceedances",
		categoryAction: "Edit",
		description: "User can edit and clear exceedances."
	};

	static readonly GENERATE_REPORTS:UserPermission = {
		ordinal: 29,
		constantName: "GENERATE_REPORTS",
		name: "Generate Reports",
		category: "Reports",
		categoryAction: "Generate",
		description: "User can generate reports."
	};

	static readonly SCHEDULE_EMAIL_REPORTS:UserPermission = {
		ordinal: 30,
		constantName: "SCHEDULE_EMAIL_REPORTS",
		name: "Schedule Email Reports",
		category: "Schedule",
		categoryAction: "Reports",
		description: "User can schedule automated email reports."
	};

	static readonly SCHEDULE_EMAIL_NOTIFICATIONS:UserPermission = {
		ordinal: 31,
		constantName: "SCHEDULE_EMAIL_NOTIFICATIONS",
		name: "Schedule Email Notifications",
		category: "Schedule",
		categoryAction: "Notifications",
		description: "User can schedule automated email notifcations."
	};

	readonly ordinal:number;
	readonly constantName:string;
	readonly name:string;
	readonly category:string;
	readonly categoryAction:string;
	readonly description:string;

	static values():UserPermission[] {
		return [
			UserPermission.ADMIN,
			UserPermission.VIEW_USERS,
			UserPermission.CREATE_USERS,
			UserPermission.CHANGE_USER_STATUS,
			UserPermission.RESET_USER_PASSWORDS,
			UserPermission.RESET_USER_USERNAMES,
			UserPermission.EDIT_USER_PROFILES,
			UserPermission.VIEW_USER_GROUPS,
			UserPermission.CREATE_USER_GROUPS,
			UserPermission.DELETE_USER_GROUPS,
			UserPermission.EDIT_USER_GROUPS,
			UserPermission.VIEW_INSTRUMENTS,
			UserPermission.CREATE_INSTRUMENTS,
			UserPermission.EDIT_INSTRUMENTS,
			UserPermission.DELETE_INSTRUMENTS,
			UserPermission.VIEW_INSTRUMENT_TYPES,
			UserPermission.CREATE_INSTRUMENT_TYPES,
			UserPermission.EDIT_INSTRUMENT_TYPES,
			UserPermission.DELETE_INSTRUMENT_TYPES,
			UserPermission.UPLOAD_MOBILE_DATA,
			UserPermission.DOWNLOAD_MOBILE_DATA,
			UserPermission.VIEW_UNVERIFIED_DATA_SETS,
			UserPermission.VIEW_UNVERIFIED_DATA_SET,
			UserPermission.EDIT_UNVERIFIED_DATA_SET,
			UserPermission.DELETE_UNVERIFIED_DATA_SET,
			UserPermission.DELETE_UNVERIFIED_DATA,
			UserPermission.COMMIT_UNVERIFIED_DATA_SET,
			UserPermission.VIEW_EXCEEDANCES,
			UserPermission.EDIT_EXCEEDANCES,
			UserPermission.GENERATE_REPORTS,
			UserPermission.SCHEDULE_EMAIL_REPORTS,
			UserPermission.SCHEDULE_EMAIL_NOTIFICATIONS
		];
	}

}