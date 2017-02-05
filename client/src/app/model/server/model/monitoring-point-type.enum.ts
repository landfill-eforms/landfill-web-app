/**
 * This enum was automatically generated from MonitoringPointType.java using typescript-gen.
 * https://github.com/landfill-eforms/landfill-tools/tree/master/typescript-gen
 */
export class MonitoringPointType {

	static readonly AMBIENT:MonitoringPointType = new MonitoringPointType(0, "AMBIENT", );
	static readonly BIOFILTER:MonitoringPointType = new MonitoringPointType(1, "BIOFILTER", );
	static readonly GRID:MonitoringPointType = new MonitoringPointType(2, "GRID", );
	static readonly GROUNDWATER:MonitoringPointType = new MonitoringPointType(3, "GROUNDWATER", );
	static readonly LEACHATE:MonitoringPointType = new MonitoringPointType(4, "LEACHATE", );
	static readonly LFG:MonitoringPointType = new MonitoringPointType(5, "LFG", );
	static readonly PILE:MonitoringPointType = new MonitoringPointType(6, "PILE", );
	static readonly PROBE:MonitoringPointType = new MonitoringPointType(7, "PROBE", );
	static readonly STORMWATER:MonitoringPointType = new MonitoringPointType(8, "STORMWATER", );

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