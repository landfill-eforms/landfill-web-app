package com.landfill_eforms.server.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="")
public class UserProfile {
	
	@ManyToOne
	@JoinColumn(name="EmailAddressFK")
	private EmailAddress emailAddress;

	public EmailAddress getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(EmailAddress emailAddress) {
		this.emailAddress = emailAddress;
	}

}
