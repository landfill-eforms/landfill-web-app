package org.lacitysan.landfill.server.persistence.enums.test;

import org.lacitysan.landfill.server.json.LandfillEnumDeserializer;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Type of test.
 * @author Alvin Quach
 */
public enum TestType {
	
	INSTANTANEOUS,
	INTEGRATED,
	PROBE,
	COMPONENT,
	GROUNDWATER,
	AMBIENT;
	
	@JsonCreator
	public static TestType deserialize(Object object) {
		return LandfillEnumDeserializer.deserialize(TestType.class, object);
	}

}
