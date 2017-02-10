import { Person } from './../model/server/persistence/entity/user/person.class';

export class StringUtils {

	static formatPersonName(person:Person):string {
		return StringUtils.formatName(person.firstname, person.middlename, person.lastname);
	}

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