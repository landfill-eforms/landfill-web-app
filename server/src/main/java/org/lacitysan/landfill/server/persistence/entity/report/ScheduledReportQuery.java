package org.lacitysan.landfill.server.persistence.entity.report;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.lacitysan.landfill.server.persistence.entity.scheduled.ScheduledReport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.ScheduledReportQueries")
@PrimaryKeyJoinColumn(name="ReportQueryFK")
@JsonInclude(Include.NON_NULL)
public class ScheduledReportQuery extends ReportQuery {

	@JsonIgnore
	@Cascade(CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name="ScheduledReportFK")
	private ScheduledReport scheduledReport;

	public ScheduledReport getScheduledReport() {
		return scheduledReport;
	}

	public void setScheduledReport(ScheduledReport scheduledReport) {
		this.scheduledReport = scheduledReport;
	}
	
}
