/**
 * This enum was automatically generated from ExceedanceType.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class ExceedanceType {

	static readonly INSTANTANEOUS:ExceedanceType = {
		ordinal: 0,
		constantName: "INSTANTANEOUS",
		name: "Instantaneous",
		shortName: "IME"
	};

	static readonly INTEGRATED:ExceedanceType = {
		ordinal: 1,
		constantName: "INTEGRATED",
		name: "Integrated",
		shortName: "ISE"
	};

	static readonly PROBE:ExceedanceType = {
		ordinal: 2,
		constantName: "PROBE",
		name: "Probe",
		shortName: "Probe"
	};

	readonly ordinal:number;
	readonly constantName:string;
	readonly name:string;
	readonly shortName:string;

	static values():ExceedanceType[] {
		return [
			ExceedanceType.INSTANTANEOUS,
			ExceedanceType.INTEGRATED,
			ExceedanceType.PROBE
		];
	}

}