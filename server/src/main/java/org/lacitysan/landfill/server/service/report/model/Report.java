package org.lacitysan.landfill.server.service.report.model;

import org.lacitysan.landfill.server.persistence.entity.report.ReportQuery;

/**
 * A generated report.
 * @author Alvin Quach
 */
public abstract class Report {
	
	/** The query parameters used to generate this report. */
	private ReportQuery reportQuery;
	
	protected Report(ReportQuery reportQuery) {
		this.reportQuery = reportQuery;
	}

	public ReportQuery getReportQuery() {
		return reportQuery;
	}

}
