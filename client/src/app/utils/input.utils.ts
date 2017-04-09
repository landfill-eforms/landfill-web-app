
/**
 * @author Alvin Quach
 */
export class InputUtils {

	static isAlphanumeric(value:string, inputStatus?:InputStatus, errorMessage:string = "Field cannot contain special characters."):boolean {
		let result:boolean = /^[a-z0-9]+$/i.test(value);
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