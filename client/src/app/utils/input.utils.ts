
/**
 * @author Alvin Quach
 */
export class InputUtils {

	static isAlphanumeric(value:string, inputStatus?:InputStatus, errorMessage:string = "Field cannot contain special characters."):boolean {
		let result:boolean = value ? /^[a-z0-9]+$/i.test(value) : true;
		if (inputStatus) {
			inputStatus.valid = result;
			inputStatus.errorMessage = result ? "" : errorMessage;
		}
		return result;
	}

}

export class InputStatus {
	valid:boolean;
	errorMessage:string;
}