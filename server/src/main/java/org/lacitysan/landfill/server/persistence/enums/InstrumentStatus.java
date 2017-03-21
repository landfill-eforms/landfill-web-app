package org.lacitysan.landfill.server.persistence.enums;

import org.lacitysan.landfill.server.json.LandfillEnumDeserializer;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Operational status of instruments.
 * @author Alvin Quach
 */
public enum InstrumentStatus {
	
	ACTIVE,
	INACTIVE,
	OBSOLETE,
	DESTROYED,
	SOLD;
	
	@JsonCreator
	public static InstrumentStatus deserialize(Object object) {
		return LandfillEnumDeserializer.deserialize(InstrumentStatus.class, object);
	}

}
