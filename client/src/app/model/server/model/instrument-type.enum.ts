/**
 * This enum was automatically generated from InstrumentType.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class InstrumentType {

	static readonly CH4_BAG:InstrumentType = new InstrumentType(0, "CH4_BAG", );
	static readonly OTHER_TOOL:InstrumentType = new InstrumentType(1, "OTHER_TOOL", );

	readonly ordinal:number;
	readonly name:string;

	private constructor(ordinal:number, name:string) {
		this.ordinal = ordinal;
		this.name = name;
	}

	static readonly values:InstrumentType[] = [
		InstrumentType.CH4_BAG,
		InstrumentType.OTHER_TOOL
	];

}