package org.lacitysan.landfill.server.persistence.entity.email;

import javax.mail.Message.RecipientType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.EmailRecipients")
@JsonInclude(Include.NON_NULL)
public class EmailRecipient implements Comparable<EmailRecipient> {
	
	@Id
	@Column(name="EmailRecipientPK")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private RecipientType type;
	
	@NotNull
	private String emailAddress;
	
	@NotNull
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public int compareTo(EmailRecipient o) {
		if (type == o.getType()) {
			if (name.equalsIgnoreCase(o.getName())) {
				return emailAddress.compareTo(o.getEmailAddress());
			}
			return name.compareToIgnoreCase(o.getName());
		}
		if (type == RecipientType.TO) {
			return 1;
		}
		if (type == RecipientType.BCC) {
			return o.getType() == RecipientType.TO ? -1 : 1;
		}
		return -1;
	}

}
