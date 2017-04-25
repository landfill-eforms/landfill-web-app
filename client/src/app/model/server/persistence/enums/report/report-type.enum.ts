/**
 * This enum was automatically generated from ReportType.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class ReportType {

	static readonly EXCEEDANCE:ReportType = {
		ordinal: 0,
		constantName: "EXCEEDANCE"
	};

	static readonly INSTANTANEOUS:ReportType = {
		ordinal: 1,
		constantName: "INSTANTANEOUS"
	};

	static readonly INTEGRATED:ReportType = {
		ordinal: 2,
		constantName: "INTEGRATED"
	};

	static readonly PROBE:ReportType = {
		ordinal: 3,
		constantName: "PROBE"
	};

	static readonly WARMSPOT:ReportType = {
		ordinal: 4,
		constantName: "WARMSPOT"
	};

	readonly ordinal:number;
	readonly constantName:string;

	static values():ReportType[] {
		return [
			ReportType.EXCEEDANCE,
			ReportType.INSTANTANEOUS,
			ReportType.INTEGRATED,
			ReportType.PROBE,
			ReportType.WARMSPOT
		];
	}

}