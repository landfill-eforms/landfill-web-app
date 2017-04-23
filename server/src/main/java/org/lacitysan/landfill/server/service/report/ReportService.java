package org.lacitysan.landfill.server.service.report;

import org.lacitysan.landfill.server.persistence.entity.report.ReportQuery;
import org.lacitysan.landfill.server.persistence.enums.report.ReportType;
import org.lacitysan.landfill.server.service.report.model.Report;

/**
 * @author Alvin Quach
 */
public class ReportService {

	public Report generateReport(ReportQuery reportQuery) {

		if (reportQuery.getReportType() == ReportType.INSTANTANEOUS) {
			
		}
		
		return null;
	}
	
}
