/**
 * This enum was automatically generated from MonitoringPointType.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class MonitoringPointType {

	static readonly AMBIENT:MonitoringPointType = new MonitoringPointType(0, "Ambient");
	static readonly BIOFILTER:MonitoringPointType = new MonitoringPointType(1, "Biofilter");
	static readonly GRID:MonitoringPointType = new MonitoringPointType(2, "Grid");
	static readonly GROUNDWATER:MonitoringPointType = new MonitoringPointType(3, "Groundwater");
	static readonly LEACHATE:MonitoringPointType = new MonitoringPointType(4, "Leachate");
	static readonly LFG:MonitoringPointType = new MonitoringPointType(5, "LFG");
	static readonly PILE:MonitoringPointType = new MonitoringPointType(6, "Pile");
	static readonly PROBE:MonitoringPointType = new MonitoringPointType(7, "Probe");
	static readonly STORMWATER:MonitoringPointType = new MonitoringPointType(8, "Stormwater");

	readonly ordinal:number;
	readonly name:string;

	private constructor(ordinal:number, name:string) {
		this.ordinal = ordinal;
		this.name = name;
	}

	static readonly values:MonitoringPointType[] = [
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