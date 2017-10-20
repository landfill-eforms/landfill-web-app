package org.lacitysan.landfill.server.persistence.enums.location;

import org.lacitysan.landfill.server.json.LandfillEnumDeserializer;
import org.lacitysan.landfill.server.persistence.enums.test.TestType;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @author Alvin Quach
 */
public enum Site {
	
	BISHOPS			("Bishops", "BC", new TestType[] {TestType.INSTANTANEOUS, TestType.INTEGRATED, TestType.PROBE}),
	
	BRANFORD		("Branford", "BR", new TestType[] {}),
	
	CLARTS			("CLARTS", "CL", new TestType[] {}),
	
	GAFFEY			("Gaffey", "GA", new TestType[] {TestType.INSTANTANEOUS, TestType.INTEGRATED, TestType.PROBE}),
	
	GRIFFITH_PARK	("Griffith Park", "GP", new TestType[] {}),
	
	LCEC			("LCEC", "LC", new TestType[] {}),
	
	LOPEZ			("Lopez", "LC", new TestType[] {TestType.INSTANTANEOUS, TestType.INTEGRATED, TestType.PROBE}),
	
	POLY_HIGH		("PolyHigh", "PH", new TestType[] {}),
	
	SHELDON			("Sheldon", "SH", new TestType[] {TestType.INSTANTANEOUS, TestType.INTEGRATED, TestType.PROBE}),
	
	TOYON			("Toyon", "TC", new TestType[] {TestType.INSTANTANEOUS, TestType.INTEGRATED, TestType.PROBE});
	
	private final String name;
	private final String shortName;
	private final TestType[] tests;
	
	private Site(String name, String shortName, TestType[] tests) {
		this.name = name;
		this.shortName = shortName;
		this.tests = tests;
	}
	
	public String getName() {
		return name;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public TestType[] getTests() {
		return tests.clone();
	}
	
	@JsonCreator
	public static Site deserialize(Object object) {
		return LandfillEnumDeserializer.deserialize(Site.class, object);
	}
	
}
