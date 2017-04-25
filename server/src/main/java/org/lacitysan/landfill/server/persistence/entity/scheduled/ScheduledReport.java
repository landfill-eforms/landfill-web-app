package org.lacitysan.landfill.server.persistence.entity.scheduled;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.lacitysan.landfill.server.persistence.entity.report.ScheduledReportQuery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.ScheduledReports")
@PrimaryKeyJoinColumn(name="ScheduledEmailFK")
@JsonInclude(Include.NON_NULL)
public class ScheduledReport extends ScheduledEmail {
	
	@JsonIgnoreProperties("scheduledReport")
	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy="scheduledReport")
	private Set<ScheduledReportQuery> reportQueries = new TreeSet<>();

	public Set<ScheduledReportQuery> getReportQueries() {
		return reportQueries;
	}

	public void setReportQueries(Set<ScheduledReportQuery> reportQueries) {
		this.reportQueries = reportQueries;
	}	

}
