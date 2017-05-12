package org.lacitysan.landfill.server.service.report.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.report.ReportQuery;
import org.lacitysan.landfill.server.service.report.model.data.WarmspotReportData;

public class WarmspotReport extends Report {
	
	List<WarmspotReportData> warmspotReportData = new ArrayList<>();
	
	public WarmspotReport(ReportQuery reportQuery, Collection<WarmspotReportData> warmspotReportData) {
		super(reportQuery);
		this.warmspotReportData.addAll(warmspotReportData);
	}

	public List<WarmspotReportData> getWarmspotReportData() {
		return warmspotReportData;
	}

	public void setWarmspotReportData(List<WarmspotReportData> warmspotReportData) {
		this.warmspotReportData = warmspotReportData;
	}

}
