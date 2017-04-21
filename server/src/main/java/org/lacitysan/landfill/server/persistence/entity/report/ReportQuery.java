package org.lacitysan.landfill.server.persistence.entity.report;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.server.persistence.enums.exceedance.ExceedanceType;
import org.lacitysan.landfill.server.persistence.enums.location.Site;
import org.lacitysan.landfill.server.persistence.enums.report.ReportType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Contains the query parameters used to generate a report. 
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.ReportQueries")
@JsonInclude(Include.NON_NULL)
public class ReportQuery {

	@Id
	@Column(name="ReportQueryPK")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(name="ReportTypeString")
	@Enumerated(EnumType.STRING)
	private ReportType reportType;
	
	@ElementCollection(targetClass=Site.class)
	@JoinTable(name="dbo.ReportQueriesXRefSites", joinColumns=@JoinColumn(name="ReportQueryFK"))
	@Column(name="SiteString")
	@Enumerated(EnumType.STRING)
	private Set<Site> sites = new HashSet<>();
	
	@ElementCollection(targetClass=ExceedanceType.class)
	@JoinTable(name="dbo.ReportQueriesXRefExceedanceTypes", joinColumns=@JoinColumn(name="ReportQueryFK"))
	@Column(name="ExceedanceTypeString")
	@Enumerated(EnumType.STRING)
	private Set<ExceedanceType> exceedanceTypes = new HashSet<>();
	
	private Date startDate;
	
	private Date endDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}

	public Set<Site> getSites() {
		return sites;
	}

	public void setSites(Set<Site> sites) {
		this.sites = sites;
	}

	public Set<ExceedanceType> getExceedanceTypes() {
		return exceedanceTypes;
	}

	public void setExceedanceTypes(Set<ExceedanceType> exceedanceTypes) {
		this.exceedanceTypes = exceedanceTypes;
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
	
}
