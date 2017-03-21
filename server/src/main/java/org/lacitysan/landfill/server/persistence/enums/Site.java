package org.lacitysan.landfill.server.persistence.enums;

import org.lacitysan.landfill.server.json.LandfillEnumDeserializer;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @author Alvin Quach
 */
public enum Site {
	
	BISHOPS			("Bishops", "BC", "", true),
	BRANFORD		("Branford", "BR", "", false),
	CLARTS			("CLARTS", "CL", "", false),
	GAFFEY			("Gaffey", "GA", "", true),
	GRIFFITH_PARK	("Griffith Park", "GP", "", false),
	LCEC			("LCEC", "LC", "", false),
	LOPEZ			("Lopez", "LC", "", true),
	POLY_HIGH		("PolyHigh", "PH", "", false),
	SHELDON			("Sheldon", "SH", "", true),
	TOYON			("Toyon", "TC", "", true);
	
	private String name;
	private String shortName;
	private String type; // TODO Make this an enum.
	private boolean active;
	
	private Site(String name, String shortName, String type, boolean active) {
		this.name = name;
		this.shortName = shortName;
		this.type = type;
		this.active = active;
	}
	
	public String getName() {
		return name;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public String getType() {
		return type;
	}
	
	public boolean isActive() {
		return active;
	}
	
	@JsonCreator
	public static Site deserialize(Object object) {
		return LandfillEnumDeserializer.deserialize(Site.class, object);
	}
	
}
