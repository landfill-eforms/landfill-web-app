package org.lacitysan.landfill.server.service.report.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A generated exceedance report.
 * @author Alvin Quach
 */
public class ExceedanceReport extends Report {
	
	List<ReportedExceedance> reportedExceedances = new ArrayList<>();

	public List<ReportedExceedance> getReportedExceedances() {
		return reportedExceedances;
	}

	public void setReportedExceedances(List<ReportedExceedance> reportedExceedances) {
		this.reportedExceedances = reportedExceedances;
	}

}
