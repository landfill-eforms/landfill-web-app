package org.lacitysan.landfill.server.persistence.entity.report;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.IndividualReportQueries")
@PrimaryKeyJoinColumn(name="ReportQueryFK")
@JsonInclude(Include.NON_NULL)
public class IndividualReportQuery extends ReportQuery {

	private Timestamp lastQueried;

	public Timestamp getLastQueried() {
		return lastQueried;
	}

	public void setLastQueried(Timestamp lastQueried) {
		this.lastQueried = lastQueried;
	}
	
}
