package org.lacitysan.landfill.server.persistence.enums.instrument;

import org.lacitysan.landfill.server.json.LandfillEnumDeserializer;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Operational status of instruments.
 * @author Alvin Quach
 */
public enum InstrumentStatus {
	
	ACTIVE 		("Active"),
	INACTIVE	("Inactive"),
	OBSOLETE	("Obsolete"),
	DESTROYED	("Destroyed"),
	SOLD		("Sold");
	
	private String name;
	
	private InstrumentStatus(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	@JsonCreator
	public static InstrumentStatus deserialize(Object object) {
		return LandfillEnumDeserializer.deserialize(InstrumentStatus.class, object);
	}

}
