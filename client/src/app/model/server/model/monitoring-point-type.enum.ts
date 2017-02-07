/**
 * This enum was automatically generated from MonitoringPointType.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class MonitoringPointType {

	static readonly AMBIENT:MonitoringPointType = new MonitoringPointType(0, "AMBIENT", "Ambient");
	static readonly BIOFILTER:MonitoringPointType = new MonitoringPointType(1, "BIOFILTER", "Biofilter");
	static readonly GRID:MonitoringPointType = new MonitoringPointType(2, "GRID", "Grid");
	static readonly GROUNDWATER:MonitoringPointType = new MonitoringPointType(3, "GROUNDWATER", "Groundwater");
	static readonly LEACHATE:MonitoringPointType = new MonitoringPointType(4, "LEACHATE", "Leachate");
	static readonly LFG:MonitoringPointType = new MonitoringPointType(5, "LFG", "LFG");
	static readonly PILE:MonitoringPointType = new MonitoringPointType(6, "PILE", "Pile");
	static readonly PROBE:MonitoringPointType = new MonitoringPointType(7, "PROBE", "Probe");
	static readonly STORMWATER:MonitoringPointType = new MonitoringPointType(8, "STORMWATER", "Stormwater");

	readonly ordinal:number;
	readonly constantName:string;
	readonly name:string;

	private constructor(ordinal:number, constantName:string, name:string) {
		this.ordinal = ordinal;
		this.constantName = constantName;
		this.name = name;
	}

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