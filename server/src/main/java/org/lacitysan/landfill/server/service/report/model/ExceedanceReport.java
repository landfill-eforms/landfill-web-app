package org.lacitysan.landfill.server.service.report.model;

import java.util.ArrayList;
import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.report.ReportQuery;
import org.lacitysan.landfill.server.service.report.model.data.ProbeExceedanceReportData;
import org.lacitysan.landfill.server.service.report.model.data.SurfaceEmissionExceedanceReportData;

/**
 * A generated exceedance report.
 * @author Alvin Quach
 */
public class ExceedanceReport extends Report {

	List<SurfaceEmissionExceedanceReportData> imeReportData = new ArrayList<>();
	List<SurfaceEmissionExceedanceReportData> iseReportData = new ArrayList<>();
	List<ProbeExceedanceReportData> probeExceedanceReportData = new ArrayList<>();

	public ExceedanceReport(ReportQuery reportQuery) {
		super(reportQuery);
	}

	public List<SurfaceEmissionExceedanceReportData> getImeReportData() {
		return imeReportData;
	}

	public void setImeReportData(List<SurfaceEmissionExceedanceReportData> imeReportData) {
		this.imeReportData = imeReportData;
	}

	public List<SurfaceEmissionExceedanceReportData> getIseReportData() {
		return iseReportData;
	}

	public void setIseReportData(List<SurfaceEmissionExceedanceReportData> iseReportData) {
		this.iseReportData = iseReportData;
	}

	public List<ProbeExceedanceReportData> getProbeExceedanceReportData() {
		return probeExceedanceReportData;
	}

	public void setProbeExceedanceReportData(List<ProbeExceedanceReportData> probeExceedanceReportData) {
		this.probeExceedanceReportData = probeExceedanceReportData;
	}

}
