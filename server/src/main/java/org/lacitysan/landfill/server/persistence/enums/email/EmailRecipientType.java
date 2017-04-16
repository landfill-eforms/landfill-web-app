package org.lacitysan.landfill.server.persistence.enums.email;

import javax.mail.Message.RecipientType;

import org.lacitysan.landfill.server.json.LandfillEnumDeserializer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

public enum EmailRecipientType {
	
	TO	(RecipientType.TO),
	CC	(RecipientType.CC),
	BCC	(RecipientType.BCC);
	
	@JsonIgnore
	private RecipientType javaxRecipientType;
	
	private EmailRecipientType(RecipientType javaxRecipientType) {
		this.javaxRecipientType = javaxRecipientType;
	}

	public RecipientType getJavaxRecipientType() {
		return javaxRecipientType;
	}
	
	public static EmailRecipientType getByJavaxRecipientType(RecipientType javaxRecipientType) {
		if (javaxRecipientType == RecipientType.TO) {
			return TO;
		}
		if (javaxRecipientType == RecipientType.CC) {
			return CC;
		}
		if (javaxRecipientType == RecipientType.BCC) {
			return BCC;
		}
		return null;
	}
	
	@JsonCreator
	public static EmailRecipientType deserialize(Object object) {
		return LandfillEnumDeserializer.deserialize(EmailRecipientType.class, object);
	}

}
