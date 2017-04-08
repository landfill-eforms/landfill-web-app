package org.lacitysan.landfill.server.persistence.enums;

import org.lacitysan.landfill.server.json.LandfillEnumDeserializer;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ScheduleRecurrence {
	
	SINGLE,
	MINUTELY,
	HOURLY,
	DAILY,
	WEEKLY,
	MONTHLY,
	QUARTERLY,
	YEARLY;
	
	@JsonCreator
	public static ScheduleRecurrence deserialize(Object object) {
		return LandfillEnumDeserializer.deserialize(ScheduleRecurrence.class, object);
	}

}
