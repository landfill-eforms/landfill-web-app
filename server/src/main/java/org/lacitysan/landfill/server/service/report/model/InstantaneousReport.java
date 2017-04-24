package org.lacitysan.landfill.server.service.report.model;

import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.report.ReportQuery;
import org.lacitysan.landfill.server.service.report.model.data.InstantaneousReportData;

/**
 * @author Alvin Quach
 */
public class InstantaneousReport extends Report {

	List<InstantaneousReportData> instantaneousReportData;
	
	public InstantaneousReport(ReportQuery reportQuery, List<InstantaneousReportData> instantaneousReportData) {
		super(reportQuery);
		this.instantaneousReportData = instantaneousReportData;
	}

	public List<InstantaneousReportData> getInstantaneousReportData() {
		return instantaneousReportData;
	}

}
