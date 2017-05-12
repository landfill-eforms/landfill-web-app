package org.lacitysan.landfill.server.persistence.enums.report;

import org.lacitysan.landfill.server.json.LandfillEnumDeserializer;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ReportPeriod {
	
	SINGLE ("Custom"),
	DAILY ("Yesterday"),
	WEEKLY ("Last Week"),
	MONTHLY ("Previous Month"),
	QUARTERLY ("Last Quarter"),
	YEARLY ("Last Year");
	
	private String name;
	
	private ReportPeriod(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@JsonCreator
	public static ReportPeriod deserialize(Object object) {
		return LandfillEnumDeserializer.deserialize(ReportPeriod.class, object);
	}

}
