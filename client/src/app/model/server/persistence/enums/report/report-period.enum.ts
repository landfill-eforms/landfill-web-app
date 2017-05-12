/**
 * This enum was automatically generated from ReportPeriod.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class ReportPeriod {

	static readonly SINGLE:ReportPeriod = {
		ordinal: 0,
		constantName: "SINGLE",
		name: "Custom"
	};

	static readonly DAILY:ReportPeriod = {
		ordinal: 1,
		constantName: "DAILY",
		name: "Yesterday"
	};

	static readonly WEEKLY:ReportPeriod = {
		ordinal: 2,
		constantName: "WEEKLY",
		name: "Last Week"
	};

	static readonly MONTHLY:ReportPeriod = {
		ordinal: 3,
		constantName: "MONTHLY",
		name: "Previous Month"
	};

	static readonly QUARTERLY:ReportPeriod = {
		ordinal: 4,
		constantName: "QUARTERLY",
		name: "Last Quarter"
	};

	static readonly YEARLY:ReportPeriod = {
		ordinal: 5,
		constantName: "YEARLY",
		name: "Last Year"
	};

	readonly ordinal:number;
	readonly constantName:string;
	readonly name:string;

	static values():ReportPeriod[] {
		return [
			ReportPeriod.SINGLE,
			ReportPeriod.DAILY,
			ReportPeriod.WEEKLY,
			ReportPeriod.MONTHLY,
			ReportPeriod.QUARTERLY,
			ReportPeriod.YEARLY
		];
	}

}