package org.lacitysan.landfill.server.persistence.enums;

import org.lacitysan.landfill.server.json.LandfillEnumDeserializer;

import com.fasterxml.jackson.annotation.JsonCreator;

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
	
	@JsonCreator
	public static MonitoringPointType deserialize(Object object) {
		return LandfillEnumDeserializer.deserialize(MonitoringPointType.class, object);
	}

}
