/**
 * This enum was automatically generated from ExceedanceStatus.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class ExceedanceStatus {

	static readonly UNVERIFIED:ExceedanceStatus = new ExceedanceStatus(0, "UNVERIFIED");
	static readonly ACTIVE:ExceedanceStatus = new ExceedanceStatus(1, "ACTIVE");
	static readonly CLOSED:ExceedanceStatus = new ExceedanceStatus(2, "CLOSED");

	readonly ordinal:number;
	readonly constantName:string;

	private constructor(ordinal:number, constantName:string) {
		this.ordinal = ordinal;
		this.constantName = constantName;
	}

	static values():ExceedanceStatus[] {
		return [
			ExceedanceStatus.UNVERIFIED,
			ExceedanceStatus.ACTIVE,
			ExceedanceStatus.CLOSED
		];
	}

}