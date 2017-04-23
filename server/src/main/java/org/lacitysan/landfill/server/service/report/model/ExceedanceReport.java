package org.lacitysan.landfill.server.service.report.model;

import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.report.ReportQuery;
import org.lacitysan.landfill.server.service.report.model.data.ExceedanceReportData;

/**
 * A generated exceedance report.
 * @author Alvin Quach
 */
public class ExceedanceReport extends Report {
	
	List<ExceedanceReportData> exceedanceReportData;

	public ExceedanceReport(ReportQuery reportQuery, List<ExceedanceReportData> exceedanceReportData) {
		super(reportQuery);
		this.exceedanceReportData = exceedanceReportData;
	}

	public List<ExceedanceReportData> getExceedanceReportData() {
		return exceedanceReportData;
	}

}
