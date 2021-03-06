package org.lacitysan.landfill.server.persistence.enums.exceedance;

import org.lacitysan.landfill.server.json.LandfillEnumDeserializer;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Type of exceedance.
 * @author Alvin Quach
 */
public enum ExceedanceType {
	
	INSTANTANEOUS ("Instantaneous Monitoring Exceedances", "IME"),
	INTEGRATED ("Integrated Sampling Exceedances", "ISE"),
	PROBE ("Probe Exceedances", "Probe"),
	COMPONENT ("Component Leaks", "Component");
	
	private String name;
	private String shortName;
	
	private ExceedanceType(String name, String shortName) {
		this.name = name;
		this.shortName = shortName;
	}

	public String getName() {
		return name;
	}

	public String getShortName() {
		return shortName;
	}
	
	@JsonCreator
	public static ExceedanceType deserialize(Object object) {
		return LandfillEnumDeserializer.deserialize(ExceedanceType.class, object);
	}

}
