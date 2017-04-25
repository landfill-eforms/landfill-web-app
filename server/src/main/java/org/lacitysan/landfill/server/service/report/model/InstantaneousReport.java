package org.lacitysan.landfill.server.service.report.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.report.ReportQuery;
import org.lacitysan.landfill.server.service.report.model.data.InstantaneousReportData;

/**
 * A generated integrated report.
 * @author Alvin Quach
 */
public class InstantaneousReport extends Report {

	List<InstantaneousReportData> instantaneousReportData = new ArrayList<>();
	
	public InstantaneousReport(ReportQuery reportQuery, Collection<InstantaneousReportData> instantaneousReportData) {
		super(reportQuery);
		this.instantaneousReportData.addAll(instantaneousReportData);
	}

	public List<InstantaneousReportData> getInstantaneousReportData() {
		return instantaneousReportData;
	}

	public void setInstantaneousReportData(List<InstantaneousReportData> instantaneousReportData) {
		this.instantaneousReportData = instantaneousReportData;
	}

}
