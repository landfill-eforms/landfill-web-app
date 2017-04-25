package org.lacitysan.landfill.server.service.report.model;

import java.util.ArrayList;
import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.report.ReportQuery;
import org.lacitysan.landfill.server.service.report.model.data.ProbeExceedanceReportData;
import org.lacitysan.landfill.server.service.report.model.data.ServiceEmissionExceedanceReportData;

/**
 * A generated exceedance report.
 * @author Alvin Quach
 */
public class ExceedanceReport extends Report {

	List<ServiceEmissionExceedanceReportData> imeReportData = new ArrayList<>();
	List<ServiceEmissionExceedanceReportData> iseReportData = new ArrayList<>();
	List<ProbeExceedanceReportData> probeExceedanceReportData = new ArrayList<>();

	public ExceedanceReport(ReportQuery reportQuery) {
		super(reportQuery);
	}

	public List<ServiceEmissionExceedanceReportData> getImeReportData() {
		return imeReportData;
	}

	public void setImeReportData(List<ServiceEmissionExceedanceReportData> imeReportData) {
		this.imeReportData = imeReportData;
	}

	public List<ServiceEmissionExceedanceReportData> getIseReportData() {
		return iseReportData;
	}

	public void setIseReportData(List<ServiceEmissionExceedanceReportData> iseReportData) {
		this.iseReportData = iseReportData;
	}

	public List<ProbeExceedanceReportData> getProbeExceedanceReportData() {
		return probeExceedanceReportData;
	}

	public void setProbeExceedanceReportData(List<ProbeExceedanceReportData> probeExceedanceReportData) {
		this.probeExceedanceReportData = probeExceedanceReportData;
	}

}
