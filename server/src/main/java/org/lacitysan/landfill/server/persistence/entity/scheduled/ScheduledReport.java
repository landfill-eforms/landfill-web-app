package org.lacitysan.landfill.server.persistence.entity.scheduled;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.entity.email.EmailRecipient;
import org.lacitysan.landfill.server.persistence.entity.user.UserGroup;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name=ApplicationConstant.DATABASE_NAME + ".dbo.ScheduledReports")
@JsonInclude(Include.NON_NULL)
public class ScheduledReport {
	
	@Id
	@Column(name="ScheduledReportPK")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String subject;
	
	@NotNull
	private String body;
	
	@JsonIgnoreProperties(value={"users", "createdBy", "modifiedBy", "scheduledReports"}, allowSetters=true)
	@ManyToMany
	@JoinTable(name=ApplicationConstant.DATABASE_NAME + ".dbo.ScheduledReportsXRefEmailRecipients", joinColumns=@JoinColumn(name="ScheduledReportFK"), inverseJoinColumns=@JoinColumn(name="UserGroupFK"))
	private Set<UserGroup> userGroups = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name=ApplicationConstant.DATABASE_NAME + ".dbo.ScheduledReportsXRefEmailRecipients", joinColumns=@JoinColumn(name="ScheduledReportFK"), inverseJoinColumns=@JoinColumn(name="EmailRecipientFK"))
	private Set<EmailRecipient> recipients = new HashSet<>();
	
}
