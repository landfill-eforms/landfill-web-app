export class EnumUtils {

	/** Converts an enum to an enum name. */
	static convertToString(input:any):any {
		let constantName = input.constantName;
		if (typeof constantName == "string") {
			return constantName
		}
		return null;
	}

	/** Converts an enum name to an enum of the given class. */
	static convertToEnum(enumClass, input:any):any {
			if (typeof input == "string") {
				let enumObj = enumClass[input];
				if (enumObj) {
					return enumObj;
				}
		}
		return null;
	}

	/** Converts an array of enums to an array of enum names. */
	static convertToStrings(input:any[]):any[] {
		let result:any[] = []
		for (let i = 0; i < input.length; i++) {
			let constantName = this.convertToString(input[i]);
			if (constantName) {
				result.push(input[i]);
			}
		}
		return result;
	}

	/** Converts an array of enum names to an array of enums of the given class. */
	static convertToEnums(enumClass, input:any[]):any[] {
		let result:any[] = []
		for (let i = 0; i < input.length; i++) {
			let enumObj = this.convertToEnum(enumClass, input[i]);
			if (enumObj) {
				result.push(enumObj);
			}
		}
		return result;
	}

}