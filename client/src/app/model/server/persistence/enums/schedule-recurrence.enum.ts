/**
 * This enum was automatically generated from ScheduleRecurrence.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class ScheduleRecurrence {

	static readonly SINGLE:ScheduleRecurrence = new ScheduleRecurrence(0, "SINGLE");
	static readonly MINUTELY:ScheduleRecurrence = new ScheduleRecurrence(1, "MINUTELY");
	static readonly HOURLY:ScheduleRecurrence = new ScheduleRecurrence(2, "HOURLY");
	static readonly DAILY:ScheduleRecurrence = new ScheduleRecurrence(3, "DAILY");
	static readonly WEEKLY:ScheduleRecurrence = new ScheduleRecurrence(4, "WEEKLY");
	static readonly MONTHLY:ScheduleRecurrence = new ScheduleRecurrence(5, "MONTHLY");
	static readonly QUARTERLY:ScheduleRecurrence = new ScheduleRecurrence(6, "QUARTERLY");
	static readonly YEARLY:ScheduleRecurrence = new ScheduleRecurrence(7, "YEARLY");

	readonly ordinal:number;
	readonly constantName:string;

	private constructor(ordinal:number, constantName:string) {
		this.ordinal = ordinal;
		this.constantName = constantName;
	}

	static values():ScheduleRecurrence[] {
		return [
			ScheduleRecurrence.SINGLE,
			ScheduleRecurrence.MINUTELY,
			ScheduleRecurrence.HOURLY,
			ScheduleRecurrence.DAILY,
			ScheduleRecurrence.WEEKLY,
			ScheduleRecurrence.MONTHLY,
			ScheduleRecurrence.QUARTERLY,
			ScheduleRecurrence.YEARLY
		];
	}

}