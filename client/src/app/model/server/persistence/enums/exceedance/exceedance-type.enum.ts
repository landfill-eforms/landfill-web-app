/**
 * This enum was automatically generated from ExceedanceType.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class ExceedanceType {

	static readonly INSTANTANEOUS: ExceedanceType = {
		ordinal: 0,
		constantName: "INSTANTANEOUS",
		name: "Instantaneous Monitoring Exceedances",
		shortName: "IME"
	};

	static readonly INTEGRATED: ExceedanceType = {
		ordinal: 1,
		constantName: "INTEGRATED",
		name: "Integrated Sampling Exceedances",
		shortName: "ISE"
	};

	static readonly PROBE: ExceedanceType = {
		ordinal: 2,
		constantName: "PROBE",
		name: "Probe Exceedances",
		shortName: "Probe"
	};

	static readonly COMPONENT: ExceedanceType = {
		ordinal: 3,
		constantName: "COMPONENT",
		name: "Component Leaks",
		shortName: "Component"
	};

	readonly ordinal:number;
	readonly constantName:string;
	readonly name: string;
	readonly shortName: string;

	static values():ExceedanceType[] {
		return [
			ExceedanceType.INSTANTANEOUS,
			ExceedanceType.INTEGRATED,
			ExceedanceType.PROBE,
			ExceedanceType.COMPONENT
		];
	}

}