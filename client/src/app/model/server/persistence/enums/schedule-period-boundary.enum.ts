/**
 * This enum was automatically generated from SchedulePeriodBoundary.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class SchedulePeriodBoundary {

	static readonly START:SchedulePeriodBoundary = new SchedulePeriodBoundary(0, "START");
	static readonly END:SchedulePeriodBoundary = new SchedulePeriodBoundary(1, "END");
	static readonly UNDEFINED:SchedulePeriodBoundary = new SchedulePeriodBoundary(2, "UNDEFINED");

	readonly ordinal:number;
	readonly constantName:string;

	private constructor(ordinal:number, constantName:string) {
		this.ordinal = ordinal;
		this.constantName = constantName;
	}

	static values():SchedulePeriodBoundary[] {
		return [
			SchedulePeriodBoundary.START,
			SchedulePeriodBoundary.END,
			SchedulePeriodBoundary.UNDEFINED
		];
	}

}