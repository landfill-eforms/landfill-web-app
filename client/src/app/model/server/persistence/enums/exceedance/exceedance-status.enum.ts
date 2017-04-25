/**
 * This enum was automatically generated from ExceedanceStatus.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class ExceedanceStatus {

	static readonly UNVERIFIED:ExceedanceStatus = {
		ordinal: 0,
		constantName: "UNVERIFIED"
	};

	static readonly ACTIVE:ExceedanceStatus = {
		ordinal: 1,
		constantName: "ACTIVE"
	};

	static readonly CLEARED:ExceedanceStatus = {
		ordinal: 2,
		constantName: "CLEARED"
	};

	readonly ordinal:number;
	readonly constantName:string;

	static values():ExceedanceStatus[] {
		return [
			ExceedanceStatus.UNVERIFIED,
			ExceedanceStatus.ACTIVE,
			ExceedanceStatus.CLEARED
		];
	}

}