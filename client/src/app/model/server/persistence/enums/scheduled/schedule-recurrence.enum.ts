/**
 * This enum was automatically generated from ScheduleRecurrence.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class ScheduleRecurrence {

	static readonly SINGLE:ScheduleRecurrence = {
		ordinal: 0,
		constantName: "SINGLE"
	};

	static readonly MINUTELY:ScheduleRecurrence = {
		ordinal: 1,
		constantName: "MINUTELY"
	};

	static readonly HOURLY:ScheduleRecurrence = {
		ordinal: 2,
		constantName: "HOURLY"
	};

	static readonly DAILY:ScheduleRecurrence = {
		ordinal: 3,
		constantName: "DAILY"
	};

	static readonly WEEKLY:ScheduleRecurrence = {
		ordinal: 4,
		constantName: "WEEKLY"
	};

	static readonly MONTHLY:ScheduleRecurrence = {
		ordinal: 5,
		constantName: "MONTHLY"
	};

	static readonly QUARTERLY:ScheduleRecurrence = {
		ordinal: 6,
		constantName: "QUARTERLY"
	};

	static readonly YEARLY:ScheduleRecurrence = {
		ordinal: 7,
		constantName: "YEARLY"
	};

	readonly ordinal:number;
	readonly constantName:string;

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