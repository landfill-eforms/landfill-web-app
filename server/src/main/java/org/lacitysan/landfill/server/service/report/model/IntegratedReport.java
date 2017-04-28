package org.lacitysan.landfill.server.service.report.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.report.ReportQuery;
import org.lacitysan.landfill.server.service.report.model.data.IntegratedReportData;

/**
 * @author Alvin Quach
 */
public class IntegratedReport extends Report {

	List<IntegratedReportData> integratedReportData = new ArrayList<>();

	public IntegratedReport(ReportQuery reportQuery, Collection<IntegratedReportData> integratedReportData) {
		super(reportQuery);
		this.integratedReportData.addAll(integratedReportData);
	}

	public List<IntegratedReportData> getIntegratedReportData() {
		return integratedReportData;
	}

	public void setIntegratedReportData(List<IntegratedReportData> integratedReportData) {
		this.integratedReportData = integratedReportData;
	}
	
}
