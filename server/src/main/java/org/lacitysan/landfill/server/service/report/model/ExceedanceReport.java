package org.lacitysan.landfill.server.service.report.model;

import java.util.ArrayList;
import java.util.List;

import org.lacitysan.landfill.server.service.report.model.data.ExceedanceReportData;

/**
 * A generated exceedance report.
 * @author Alvin Quach
 */
public class ExceedanceReport extends Report {
	
	List<ExceedanceReportData> reportedExceedances = new ArrayList<>();

	public List<ExceedanceReportData> getReportedExceedances() {
		return reportedExceedances;
	}

	public void setReportedExceedances(List<ExceedanceReportData> reportedExceedances) {
		this.reportedExceedances = reportedExceedances;
	}

}
