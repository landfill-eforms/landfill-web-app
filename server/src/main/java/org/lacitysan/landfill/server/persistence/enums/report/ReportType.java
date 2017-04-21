package org.lacitysan.landfill.server.persistence.enums.report;

import org.lacitysan.landfill.server.json.LandfillEnumDeserializer;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Type of report to be generated.
 * @author Alvin Quach
 */
public enum ReportType {
	
	EXCEEDANCE,
	INSTANTANEOUS,
	INTEGRATED,
	PROBE,
	WARMSPOT;
	
	@JsonCreator
	public static ReportType deserialize(Object object) {
		return LandfillEnumDeserializer.deserialize(ReportType.class, object);
	}

}
