package org.lacitysan.landfill.server.persistence.enums;

/**
 * @author Alvin Quach
 */
public enum MonitoringPointType {
	
	AMBIENT		("Ambient"), 
	BIOFILTER	("Biofilter"), 
	GRID		("Grid"), 
	GROUNDWATER	("Groundwater"), 
	LEACHATE	("Leachate"), 
	LFG			("LFG"), 
	PILE		("Pile"), 
	PROBE		("Probe"), 
	STORMWATER	("Stormwater");
	
	private MonitoringPointType(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}

}
