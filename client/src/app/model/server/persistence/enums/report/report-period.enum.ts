/**
 * This enum was automatically generated from ReportPeriod.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class ReportPeriod {

	static readonly SINGLE: ReportPeriod = {
		ordinal: 0,
		constantName: "SINGLE",
		name: "Custom"
	};

	static readonly DAILY: ReportPeriod = {
		ordinal: 1,
		constantName: "DAILY",
		name: "Day"
	};

	static readonly WEEK: ReportPeriod = {
		ordinal: 2,
		constantName: "WEEK",
		name: "Week"
	};

	static readonly MONTH: ReportPeriod = {
		ordinal: 3,
		constantName: "MONTH",
		name: "Month"
	};

	static readonly QUARTER: ReportPeriod = {
		ordinal: 4,
		constantName: "QUARTER",
		name: "Quarter"
	};

	static readonly YEAR: ReportPeriod = {
		ordinal: 5,
		constantName: "YEAR",
		name: "Year"
	};

	readonly ordinal:number;
	readonly constantName:string;
	readonly name: string;

	static values():ReportPeriod[] {
		return [
			ReportPeriod.SINGLE,
			ReportPeriod.DAILY,
			ReportPeriod.WEEK,
			ReportPeriod.MONTH,
			ReportPeriod.QUARTER,
			ReportPeriod.YEAR
		];
	}

}