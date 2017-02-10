/**
 * This enum was automatically generated from IMENumberStatus.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class IMENumberStatus {

	static readonly UNVERIFIED:IMENumberStatus = new IMENumberStatus(0, "UNVERIFIED", );
	static readonly ACTIVE:IMENumberStatus = new IMENumberStatus(1, "ACTIVE", );
	static readonly CLOSED:IMENumberStatus = new IMENumberStatus(2, "CLOSED", );

	readonly ordinal:number;
	readonly constantName:string;

	private constructor(ordinal:number, constantName:string) {
		this.ordinal = ordinal;
		this.constantName = constantName;
	}

	static values():IMENumberStatus[] {
		return [
			IMENumberStatus.UNVERIFIED,
			IMENumberStatus.ACTIVE,
			IMENumberStatus.CLOSED
		];
	}

}