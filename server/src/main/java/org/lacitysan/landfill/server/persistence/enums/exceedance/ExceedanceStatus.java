package org.lacitysan.landfill.server.persistence.enums.exceedance;

import org.lacitysan.landfill.server.json.LandfillEnumDeserializer;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @author Alvin Quach
 */
public enum ExceedanceStatus {
	
	UNVERIFIED	("Unverified"),
	ACTIVE		("Active"),
	CLEARED		("Cleared");
	
	private String name;
	
	private ExceedanceStatus(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@JsonCreator
	public static ExceedanceStatus deserialize(Object object) {
		return LandfillEnumDeserializer.deserialize(ExceedanceStatus.class, object);
	}
	
}
