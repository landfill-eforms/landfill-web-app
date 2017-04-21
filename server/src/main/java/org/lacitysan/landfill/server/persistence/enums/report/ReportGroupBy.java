package org.lacitysan.landfill.server.persistence.enums.report;

import org.lacitysan.landfill.server.json.LandfillEnumDeserializer;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @author Alvin Quach
 */
public enum ReportGroupBy {
	
	DATE,
	TYPE,
	LOCATION;

	@JsonCreator
	public static ReportGroupBy deserialize(Object object) {
		return LandfillEnumDeserializer.deserialize(ReportGroupBy.class, object);
	}
	
}
