package org.lacitysan.landfill.server.service.report.model;

import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.report.ReportQuery;
import org.lacitysan.landfill.server.service.report.model.data.IntegratedReportData;

/**
 * @author Alvin Quach
 */
public class IntegratedReport extends Report {

	List<IntegratedReportData> integratedReportData;

	public IntegratedReport(ReportQuery reportQuery, List<IntegratedReportData> integratedReportData) {
		super(reportQuery);
		this.integratedReportData = integratedReportData;
	}

	public List<IntegratedReportData> getIntegratedReportData() {
		return integratedReportData;
	}
}
