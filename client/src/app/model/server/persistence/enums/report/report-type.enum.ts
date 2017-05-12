/**
 * This enum was automatically generated from ReportType.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class ReportType {

	static readonly EXCEEDANCE:ReportType = {
		ordinal: 0,
		constantName: "EXCEEDANCE",
		name: "Exceedance"
	};

	static readonly INSTANTANEOUS:ReportType = {
		ordinal: 1,
		constantName: "INSTANTANEOUS",
		name: "Instantaneous"
	};

	static readonly INTEGRATED:ReportType = {
		ordinal: 2,
		constantName: "INTEGRATED",
		name: "Integrated"
	};

	static readonly PROBE:ReportType = {
		ordinal: 3,
		constantName: "PROBE",
		name: "Probe"
	};

	static readonly WARMSPOT:ReportType = {
		ordinal: 4,
		constantName: "WARMSPOT",
		name: "Warmspot"
	};

	readonly ordinal:number;
	readonly constantName:string;
	readonly name:string;

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