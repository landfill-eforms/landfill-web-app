package org.lacitysan.landfill.server.persistence.enums.scheduled;

import org.lacitysan.landfill.server.json.LandfillEnumDeserializer;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ScheduleRecurrence {
	
	SINGLE		("One Time"),
	MINUTELY	("Minutely"),
	HOURLY		("Hourly"),
	DAILY		("Daily"),
	WEEKLY		("Weekly"),
	MONTHLY		("Monthly"),
	QUARTERLY	("Quarterly"),
	YEARLY		("Yearly");
	
	private String name;
	
	private ScheduleRecurrence(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@JsonCreator
	public static ScheduleRecurrence deserialize(Object object) {
		return LandfillEnumDeserializer.deserialize(ScheduleRecurrence.class, object);
	}

}
