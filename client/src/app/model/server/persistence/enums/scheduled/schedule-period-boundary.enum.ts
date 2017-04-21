/**
 * This enum was automatically generated from SchedulePeriodBoundary.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class SchedulePeriodBoundary {

	static readonly START:SchedulePeriodBoundary = {
		ordinal: 0,
		constantName: "START"
	};

	static readonly END:SchedulePeriodBoundary = {
		ordinal: 1,
		constantName: "END"
	};

	static readonly UNDEFINED:SchedulePeriodBoundary = {
		ordinal: 2,
		constantName: "UNDEFINED"
	};

	readonly ordinal:number;
	readonly constantName:string;

	static values():SchedulePeriodBoundary[] {
		return [
			SchedulePeriodBoundary.START,
			SchedulePeriodBoundary.END,
			SchedulePeriodBoundary.UNDEFINED
		];
	}

}