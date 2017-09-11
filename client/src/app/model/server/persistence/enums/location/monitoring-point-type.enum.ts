/**
 * This enum was automatically generated from MonitoringPointType.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class MonitoringPointType {

	static readonly AMBIENT: MonitoringPointType = {
		ordinal: 0,
		constantName: "AMBIENT",
		name: "Ambient"
	};

	static readonly BIOFILTER: MonitoringPointType = {
		ordinal: 1,
		constantName: "BIOFILTER",
		name: "Biofilter"
	};

	static readonly GRID: MonitoringPointType = {
		ordinal: 2,
		constantName: "GRID",
		name: "Grid"
	};

	static readonly GROUNDWATER: MonitoringPointType = {
		ordinal: 3,
		constantName: "GROUNDWATER",
		name: "Groundwater"
	};

	static readonly LEACHATE: MonitoringPointType = {
		ordinal: 4,
		constantName: "LEACHATE",
		name: "Leachate"
	};

	static readonly LFG: MonitoringPointType = {
		ordinal: 5,
		constantName: "LFG",
		name: "LFG"
	};

	static readonly PILE: MonitoringPointType = {
		ordinal: 6,
		constantName: "PILE",
		name: "Pile"
	};

	static readonly PROBE: MonitoringPointType = {
		ordinal: 7,
		constantName: "PROBE",
		name: "Probe"
	};

	static readonly STORMWATER: MonitoringPointType = {
		ordinal: 8,
		constantName: "STORMWATER",
		name: "Stormwater"
	};

	readonly ordinal:number;
	readonly constantName:string;
	readonly name: string;

	static values():MonitoringPointType[] {
		return [
			MonitoringPointType.AMBIENT,
			MonitoringPointType.BIOFILTER,
			MonitoringPointType.GRID,
			MonitoringPointType.GROUNDWATER,
			MonitoringPointType.LEACHATE,
			MonitoringPointType.LFG,
			MonitoringPointType.PILE,
			MonitoringPointType.PROBE,
			MonitoringPointType.STORMWATER
		];
	}

}