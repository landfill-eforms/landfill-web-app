package org.lacitysan.landfill.server.json;

import java.util.Map;

public class LandfillEnumDeserializer {
	
	public static <T extends Enum<T>> T deserialize(Class<T> enumType, Object object) {
		String name;
		if (object instanceof String) {
			name = object.toString();
		}
		else if (object instanceof Map) {
			name = ((Map<?, ?>)object).get("constantName").toString();
		}
		else return null;
		return Enum.valueOf(enumType, name);
	}

}
