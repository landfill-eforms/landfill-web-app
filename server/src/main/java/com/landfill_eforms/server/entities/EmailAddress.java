package com.landfill_eforms.server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="test.dbo.EmailAddresses")
public class EmailAddress {
	
	@Id
	@Column(name="EmailAddressPK")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String localPart;
	
	@NotNull
	private String domain;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocalPart() {
		return localPart;
	}

	public void setLocalPart(String localPart) {
		this.localPart = localPart;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	@Override
	public String toString() {
		return localPart + "@" + domain;
	}
	
	/**
	 * Creates a new <code>EmailAddress</code> based on an input string.
	 * Returns <code>null</code> if the string is not a valid email address.
	 * @param emailAddress A string representation of an email address.
	 * @return The <code>EmailAddress</code> representation of the input string,
	 * 		   or <code>null</code> if the string is not a vaild email address.
	 */
	public static EmailAddress fromString(String emailAddress) {
		String[] parts = emailAddress.split("@");
		if (parts.length == 2) {
			EmailAddress result = new EmailAddress();
			result.setLocalPart(parts[0]);
			result.setDomain(parts[1]);
		}
		return null;
	}
	
}
