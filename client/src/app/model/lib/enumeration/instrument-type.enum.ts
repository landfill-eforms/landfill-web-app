/**
 * This enum was automatically generated from InstrumentType.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class InstrumentType {

	static readonly CH4_BAG:InstrumentType = new InstrumentType(0);
	static readonly OTHER_TOOL:InstrumentType = new InstrumentType(1);

	readonly ordinal:number;

	private constructor(ordinal:number, ) {
		this.ordinal = ordinal;
	}

	static readonly values:InstrumentType[] = [
		InstrumentType.CH4_BAG,
		InstrumentType.OTHER_TOOL
	];

}