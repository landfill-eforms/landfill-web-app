package org.lacitysan.landfill.server.persistence.enums;

import org.lacitysan.landfill.server.json.LandfillEnumDeserializer;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum SchedulePeriodBoundary {
	
	START,
	END,
	UNDEFINED;
	
	@JsonCreator
	public static SchedulePeriodBoundary deserialize(Object object) {
		return LandfillEnumDeserializer.deserialize(SchedulePeriodBoundary.class, object);
	}

}
