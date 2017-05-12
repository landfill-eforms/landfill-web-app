import { User } from './../model/server/persistence/entity/user/user.class';
export class StringUtils {

	/** Takes a user object and outputs a name string in the following format: Last, First Middle. */
	static formatUserName(user:User):string {
		if (user == null) {
			return "";
		}
		return StringUtils.formatName(user.firstname, user.middlename, user.lastname);
	}

	/** Takes a first name, a middle name, and a last name and outputs a name string in the following format: Last, First Middle. */
	static formatName(firstname?:string, middlename?:string, lastname?:string):string {
		if (!firstname && !lastname) {
			return "";
		}
		if (!firstname) {
			return lastname;
		}
		let firstMI:string = firstname + (middlename ? " " + middlename.charAt(0) + "." : "");
		if (!lastname) {
			return firstMI;
		}
		return lastname + ", " + firstMI;
	}

}