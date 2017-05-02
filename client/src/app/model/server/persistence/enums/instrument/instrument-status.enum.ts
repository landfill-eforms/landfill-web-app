/**
 * This enum was automatically generated from InstrumentStatus.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class InstrumentStatus {

	static readonly ACTIVE:InstrumentStatus = {
		ordinal: 0,
		constantName: "ACTIVE",
		name: "Active"
	};

	static readonly INACTIVE:InstrumentStatus = {
		ordinal: 1,
		constantName: "INACTIVE",
		name: "Inactive"
	};

	static readonly OBSOLETE:InstrumentStatus = {
		ordinal: 2,
		constantName: "OBSOLETE",
		name: "Obsolete"
	};

	static readonly DESTROYED:InstrumentStatus = {
		ordinal: 3,
		constantName: "DESTROYED",
		name: "Destroyed"
	};

	static readonly SOLD:InstrumentStatus = {
		ordinal: 4,
		constantName: "SOLD",
		name: "Sold"
	};

	readonly ordinal:number;
	readonly constantName:string;
	readonly name:string;

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