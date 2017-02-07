export class EnumUtils {

	/** Converts an array of enums to an array of enum names. */
	static convertToStrings(input:any[]):any[] {
		let result:any[] = []
		for (let i = 0; i < input.length; i++) {
			let constantName = input[i].constantName;
			if (typeof constantName == "string") {
				result.push(constantName);
			}
		}
		return result;
	}

	/** Converts an array of enum names to an array of enums of the given class. */
	static convertToEnums(enumClass, input:any[]):any[] {
				let result:any[] = []
		for (let i = 0; i < input.length; i++) {
			let name = input[i];
			if (typeof name == "string") {
				let enumObj = enumClass[name];
				if (enumObj) {
					result.push(enumObj);
				}
			}
		}
		return result;
	}

}