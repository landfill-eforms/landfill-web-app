/**
 * This enum was automatically generated from ImeNumberStatus.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class ImeNumberStatus {

	static readonly UNVERIFIED:ImeNumberStatus = new ImeNumberStatus(0, "UNVERIFIED");
	static readonly ACTIVE:ImeNumberStatus = new ImeNumberStatus(1, "ACTIVE");
	static readonly CLOSED:ImeNumberStatus = new ImeNumberStatus(2, "CLOSED");

	readonly ordinal:number;
	readonly constantName:string;

	private constructor(ordinal:number, constantName:string) {
		this.ordinal = ordinal;
		this.constantName = constantName;
	}

	static values():ImeNumberStatus[] {
		return [
			ImeNumberStatus.UNVERIFIED,
			ImeNumberStatus.ACTIVE,
			ImeNumberStatus.CLOSED
		];
	}

}