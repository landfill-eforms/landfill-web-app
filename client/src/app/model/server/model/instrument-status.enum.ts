/**
 * This enum was automatically generated from InstrumentStatus.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class InstrumentStatus {

	static readonly ACTIVE:InstrumentStatus = new InstrumentStatus(0, "ACTIVE");
	static readonly INACTIVE:InstrumentStatus = new InstrumentStatus(1, "INACTIVE");
	static readonly OBSOLETE:InstrumentStatus = new InstrumentStatus(2, "OBSOLETE");
	static readonly DESTROYED:InstrumentStatus = new InstrumentStatus(3, "DESTROYED");
	static readonly SOLD:InstrumentStatus = new InstrumentStatus(4, "SOLD");

	readonly ordinal:number;
	readonly constantName:string;

	private constructor(ordinal:number, constantName:string) {
		this.ordinal = ordinal;
		this.constantName = constantName;
	}

	static values():InstrumentStatus[] {
		return [
			InstrumentStatus.ACTIVE,
			InstrumentStatus.INACTIVE,
			InstrumentStatus.OBSOLETE,
			InstrumentStatus.DESTROYED,
			InstrumentStatus.SOLD
		];
	}

}