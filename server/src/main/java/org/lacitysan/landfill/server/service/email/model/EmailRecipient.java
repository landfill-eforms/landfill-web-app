package org.lacitysan.landfill.server.service.email.model;

import javax.mail.Message.RecipientType;

/**
 * @author Alvin Quach
 */
public class EmailRecipient {

	private RecipientType type;
	private String emailAddress;
	private String name;

	public EmailRecipient() {}
	
	public EmailRecipient(String emailAddress, String name) {
		this.type = RecipientType.TO;
		this.emailAddress = emailAddress;
		this.name = name;
	}
	
	public EmailRecipient(RecipientType type, String emailAddress, String name) {
		this.type = type;
		this.emailAddress = emailAddress;
		this.name = name;
	}

	public RecipientType getType() {
		return type;
	}

	public void setType(RecipientType type) {
		this.type = type;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
