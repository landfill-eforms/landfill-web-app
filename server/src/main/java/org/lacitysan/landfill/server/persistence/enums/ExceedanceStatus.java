package org.lacitysan.landfill.server.persistence.enums;

import org.lacitysan.landfill.server.json.LandfillEnumDeserializer;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ExceedanceStatus {
	
	UNVERIFIED,
	ACTIVE,
	CLOSED;
	
	@JsonCreator
	public static ExceedanceStatus deserialize(Object object) {
		return LandfillEnumDeserializer.deserialize(ExceedanceStatus.class, object);
	}
	
}
