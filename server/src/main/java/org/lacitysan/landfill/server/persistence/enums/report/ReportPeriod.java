package org.lacitysan.landfill.server.persistence.enums.report;

import org.lacitysan.landfill.server.json.LandfillEnumDeserializer;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ReportPeriod {
	
	SINGLE ("Custom"),
	DAILY ("Day"),
	WEEK ("Week"),
	MONTH ("Month"),
	QUARTER ("Quarter"),
	YEAR ("Year");
	
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
