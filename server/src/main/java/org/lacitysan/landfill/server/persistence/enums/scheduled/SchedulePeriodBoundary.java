package org.lacitysan.landfill.server.persistence.enums.scheduled;

import org.lacitysan.landfill.server.json.LandfillEnumDeserializer;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum SchedulePeriodBoundary {
	
	START		("Start"),
	END 		("End"),
	UNDEFINED	("Undefined");
	
	private String name;
	
	private SchedulePeriodBoundary(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@JsonCreator
	public static SchedulePeriodBoundary deserialize(Object object) {
		return LandfillEnumDeserializer.deserialize(SchedulePeriodBoundary.class, object);
	}

}
