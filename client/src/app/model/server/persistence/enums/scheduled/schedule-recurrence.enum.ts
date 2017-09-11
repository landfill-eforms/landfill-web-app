/**
 * This enum was automatically generated from ScheduleRecurrence.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class ScheduleRecurrence {

	static readonly SINGLE: ScheduleRecurrence = {
		ordinal: 0,
		constantName: "SINGLE",
		name: "One Time"
	};

	static readonly MINUTELY: ScheduleRecurrence = {
		ordinal: 1,
		constantName: "MINUTELY",
		name: "Minutely"
	};

	static readonly HOURLY: ScheduleRecurrence = {
		ordinal: 2,
		constantName: "HOURLY",
		name: "Hourly"
	};

	static readonly DAILY: ScheduleRecurrence = {
		ordinal: 3,
		constantName: "DAILY",
		name: "Daily"
	};

	static readonly WEEKLY: ScheduleRecurrence = {
		ordinal: 4,
		constantName: "WEEKLY",
		name: "Weekly"
	};

	static readonly MONTHLY: ScheduleRecurrence = {
		ordinal: 5,
		constantName: "MONTHLY",
		name: "Monthly"
	};

	static readonly QUARTERLY: ScheduleRecurrence = {
		ordinal: 6,
		constantName: "QUARTERLY",
		name: "Quarterly"
	};

	static readonly YEARLY: ScheduleRecurrence = {
		ordinal: 7,
		constantName: "YEARLY",
		name: "Yearly"
	};

	readonly ordinal:number;
	readonly constantName:string;
	readonly name: string;

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