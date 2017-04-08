package org.lacitysan.landfill.server.persistence.entity.email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.server.persistence.entity.scheduled.ScheduledEmail;
import org.lacitysan.landfill.server.persistence.enums.EmailRecipientType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
	@Column(name="RecipientTypeString")
	@Enumerated(EnumType.STRING)
	private EmailRecipientType recipientType;
	
	@NotNull
	private String emailAddress;
	
	@NotNull
	private String name;
	
	@JsonIgnoreProperties(value={"recipients"}, allowSetters=true)
	@NotNull
	@ManyToOne
	@JoinColumn(name="ScheduledEmailFK")
	private ScheduledEmail scheduledEmail;

	public EmailRecipient() {}
	
	public EmailRecipient(String emailAddress, String name) {
		this.recipientType = EmailRecipientType.TO;
		this.emailAddress = emailAddress;
		this.name = name;
	}
	
	public EmailRecipient(EmailRecipientType type, String emailAddress, String name) {
		this.recipientType = type;
		this.emailAddress = emailAddress;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EmailRecipientType getRecipientType() {
		return recipientType;
	}

	public void setRecipientType(EmailRecipientType recipientType) {
		this.recipientType = recipientType;
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

	public ScheduledEmail getScheduledEmail() {
		return scheduledEmail;
	}

	public void setScheduledEmail(ScheduledEmail scheduledEmail) {
		this.scheduledEmail = scheduledEmail;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(EmailRecipient o) {
		int compare = recipientType.ordinal() - o.getRecipientType().ordinal();
		if (compare != 0) {
			return compare;
		}
		compare = name.compareToIgnoreCase(o.getName());
		if (compare != 0) {
			return compare;
		}
		return emailAddress.compareToIgnoreCase(o.getEmailAddress());
	}

}
