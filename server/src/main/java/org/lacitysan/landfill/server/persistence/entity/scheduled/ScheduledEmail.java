package org.lacitysan.landfill.server.persistence.entity.scheduled;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.lacitysan.landfill.server.persistence.entity.email.EmailRecipient;
import org.lacitysan.landfill.server.persistence.entity.user.UserGroup;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.ScheduledEmails")
@Inheritance(strategy=InheritanceType.JOINED)
@JsonInclude(Include.NON_NULL)
public abstract class ScheduledEmail {

	@Id
	@Column(name="ScheduledEmailPK")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	@JoinColumn(name="ScheduleFK")
	@Cascade(CascadeType.ALL)
	private Schedule schedule;

	@NotNull
	private String subject;

	@NotNull
	private String body;

	@JsonIgnoreProperties(value={"users", "createdBy", "modifiedBy", "scheduledEmails"}, allowSetters=true) 
	@ManyToMany 
	@JoinTable(name="dbo.ScheduledEmailsXRefUserGroups", joinColumns=@JoinColumn(name="ScheduledEmailFK"), inverseJoinColumns=@JoinColumn(name="UserGroupFK")) 
	private Set<UserGroup> userGroups = new HashSet<>(); 

	@JsonIgnoreProperties(value={"scheduledEmail"}, allowSetters=true) 
	@OneToMany(mappedBy="scheduledEmail")
	@Cascade(CascadeType.ALL)
	private Set<EmailRecipient> recipients = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Set<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Set<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

	public Set<EmailRecipient> getRecipients() {
		return recipients;
	}

	public void setRecipients(Set<EmailRecipient> recipients) {
		this.recipients = recipients;
	} 

}
