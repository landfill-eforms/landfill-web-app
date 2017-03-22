package org.lacitysan.landfill.server.json;

import java.util.Map;

/**
 * Custom JSON deserializer for enums in the Landfill e-Forms web appplication.
 * @author Alvin Quach
 */
public class LandfillEnumDeserializer {
	
	/** 
	 * Allows enums in incoming JSON objects to be represented either by string or the entire enum object. 
	 * Assumes that an enum constant's name is stored in a property called 'constantName'.
	 */
	public static <T extends Enum<T>> T deserialize(Class<T> enumType, Object object) {
		String name;
		if (object instanceof String) {
			name = object.toString();
		}
		else if (object instanceof Map) {
			name = ((Map<?, ?>)object).get("constantName").toString();
		}
		else {
			return null;
		}
		return Enum.valueOf(enumType, name);
	}

}
