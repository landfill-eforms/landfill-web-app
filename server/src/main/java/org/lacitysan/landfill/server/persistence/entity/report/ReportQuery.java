package org.lacitysan.landfill.server.persistence.entity.report;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.server.persistence.entity.AbstractEntity;
import org.lacitysan.landfill.server.persistence.enums.exceedance.ExceedanceType;
import org.lacitysan.landfill.server.persistence.enums.location.Site;
import org.lacitysan.landfill.server.persistence.enums.report.ReportPeriod;
import org.lacitysan.landfill.server.persistence.enums.report.ReportType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Contains the query parameters used to generate a report. 
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.ReportQueries")
@Inheritance(strategy=InheritanceType.JOINED)
@AttributeOverride(name="id", column=@Column(name="ReportQueryPK"))
@JsonInclude(Include.NON_NULL)
public abstract class ReportQuery extends AbstractEntity implements Comparable<ReportQuery> {
	
	@NotNull
	@Column(name="ReportTypeString")
	@Enumerated(EnumType.STRING)
	private ReportType reportType;
	
	@NotNull
	@Column(name="SiteString")
	@Enumerated(EnumType.STRING)
	private Site site;
	
	@ElementCollection(targetClass=ExceedanceType.class)
	@JoinTable(name="dbo.ReportQueriesXRefExceedanceTypes", joinColumns=@JoinColumn(name="ReportQueryFK"))
	@Column(name="ExceedanceTypeString")
	@Enumerated(EnumType.STRING)
	private Set<ExceedanceType> exceedanceTypes = new HashSet<>();
	
	@NotNull
	@Column(name="ReportPeriodString")
	@Enumerated(EnumType.STRING)
	private ReportPeriod reportPeriod;
	
	private Integer periodOffset;
	
	private Boolean periodToDate;
	
	private Date startDate;
	
	private Date endDate;
	
	private Timestamp dateCreated;

	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Set<ExceedanceType> getExceedanceTypes() {
		return exceedanceTypes;
	}

	public void setExceedanceTypes(Set<ExceedanceType> exceedanceTypes) {
		this.exceedanceTypes = exceedanceTypes;
	}

	public ReportPeriod getReportPeriod() {
		return reportPeriod;
	}

	public void setReportPeriod(ReportPeriod reportPeriod) {
		this.reportPeriod = reportPeriod;
	}

	public Integer getPeriodOffset() {
		return periodOffset;
	}

	public void setPeriodOffset(Integer periodOffset) {
		this.periodOffset = periodOffset;
	}

	public Boolean getPeriodToDate() {
		return periodToDate;
	}

	public void setPeriodToDate(Boolean periodToDate) {
		this.periodToDate = periodToDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@Override
	public int compareTo(ReportQuery o) {
		return dateCreated.compareTo(o.getDateCreated());
	}
	
}
