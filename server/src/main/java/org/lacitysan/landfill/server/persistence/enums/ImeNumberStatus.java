package org.lacitysan.landfill.server.persistence.enums;

import org.lacitysan.landfill.server.json.LandfillEnumDeserializer;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ImeNumberStatus {
	
	UNVERIFIED,
	ACTIVE,
	CLOSED;
	
	@JsonCreator
	public static ImeNumberStatus deserialize(Object object) {
		return LandfillEnumDeserializer.deserialize(ImeNumberStatus.class, object);
	}
	
}
