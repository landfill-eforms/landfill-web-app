/**
 * This enum was automatically generated from EmailRecipientType.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class EmailRecipientType {

	static readonly TO:EmailRecipientType = new EmailRecipientType(0, "TO");
	static readonly CC:EmailRecipientType = new EmailRecipientType(1, "CC");
	static readonly BCC:EmailRecipientType = new EmailRecipientType(2, "BCC");

	readonly ordinal:number;
	readonly constantName:string;

	private constructor(ordinal:number, constantName:string) {
		this.ordinal = ordinal;
		this.constantName = constantName;
	}

	static values():EmailRecipientType[] {
		return [
			EmailRecipientType.TO,
			EmailRecipientType.CC,
			EmailRecipientType.BCC
		];
	}

}