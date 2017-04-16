/**
 * This enum was automatically generated from EmailRecipientType.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class EmailRecipientType {

	static readonly TO:EmailRecipientType = {
		ordinal: 0,
		constantName: "TO"
	};

	static readonly CC:EmailRecipientType = {
		ordinal: 1,
		constantName: "CC"
	};

	static readonly BCC:EmailRecipientType = {
		ordinal: 2,
		constantName: "BCC"
	};

	readonly ordinal:number;
	readonly constantName:string;

	static values():EmailRecipientType[] {
		return [
			EmailRecipientType.TO,
			EmailRecipientType.CC,
			EmailRecipientType.BCC
		];
	}

}