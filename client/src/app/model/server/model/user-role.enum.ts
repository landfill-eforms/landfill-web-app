/**
 * This enum was automatically generated from UserRole.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class UserRole {

	static readonly SUPER_ADMIN:UserRole = new UserRole(0, "SUPER_ADMIN", "Super Admin", null, null, "Super Admin");
	static readonly ADMIN:UserRole = new UserRole(1, "ADMIN", "Admin", null, null, "Admin");
	static readonly CREATE_USERS:UserRole = new UserRole(2, "CREATE_USERS", "Create Users", "Users", "Create", "User can create new users.");
	static readonly DEACTIVATE_USERS:UserRole = new UserRole(3, "DEACTIVATE_USERS", "Deactivate Users", "Users", "Deactivate", "User can deactive other non-admin users.");
	static readonly RESET_USER_PASSWORDS:UserRole = new UserRole(4, "RESET_USER_PASSWORDS", "Reset User Passwords", "Users", "Reset Password", "User can reset the passwords of other non-admin users.");
	static readonly RESET_USER_USERNAMES:UserRole = new UserRole(5, "RESET_USER_USERNAMES", "Reset User Usernames", "Users", "Reset Username", "User can reset the usernames of other non-admin users.");
	static readonly EDIT_USER_PROFILES:UserRole = new UserRole(6, "EDIT_USER_PROFILES", "Edit User Profiles", "Users", "Edit Profile", "User can edit the profiles of other non-admin users.");
	static readonly ASSIGN_EMPLOYEE_ID:UserRole = new UserRole(7, "ASSIGN_EMPLOYEE_ID", "Assign Employee ID", "Users", "Assign Employee ID", "User can change the employee ID associated with non-admin user accounts.");
	static readonly ASSIGN_USER_GROUPS:UserRole = new UserRole(8, "ASSIGN_USER_GROUPS", "Assign User Groups", "Users", "Assign Groups", "User can assign user groups to non-admin users.");
	static readonly CREATE_USER_GROUPS:UserRole = new UserRole(9, "CREATE_USER_GROUPS", "Create User Groups", "User Groups", "Create", "User can create new user groups.");
	static readonly DELETE_USER_GROUPS:UserRole = new UserRole(10, "DELETE_USER_GROUPS", "Delete User Groups", "User Groups", "Delete", "User can delete existing user groups.");
	static readonly EDIT_USER_GROUPS:UserRole = new UserRole(11, "EDIT_USER_GROUPS", "Edit User Groups", "User Groups", "Edit", "User can add/remove roles and rename existing user groups.");
	static readonly TEST:UserRole = new UserRole(12, "TEST", "Test", null, null, "Testing 123");

	readonly ordinal:number;
	readonly constantName:string;
	readonly name:string;
	readonly group:string;
	readonly groupAction:string;
	readonly description:string;

	private constructor(ordinal:number, constantName:string, name:string, group:string, groupAction:string, description:string) {
		this.ordinal = ordinal;
		this.constantName = constantName;
		this.name = name;
		this.group = group;
		this.groupAction = groupAction;
		this.description = description;
	}

	static readonly values:UserRole[] = [
		UserRole.SUPER_ADMIN,
		UserRole.ADMIN,
		UserRole.CREATE_USERS,
		UserRole.DEACTIVATE_USERS,
		UserRole.RESET_USER_PASSWORDS,
		UserRole.RESET_USER_USERNAMES,
		UserRole.EDIT_USER_PROFILES,
		UserRole.ASSIGN_EMPLOYEE_ID,
		UserRole.ASSIGN_USER_GROUPS,
		UserRole.CREATE_USER_GROUPS,
		UserRole.DELETE_USER_GROUPS,
		UserRole.EDIT_USER_GROUPS,
		UserRole.TEST
	];

}