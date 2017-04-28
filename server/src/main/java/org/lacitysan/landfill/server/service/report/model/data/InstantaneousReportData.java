package org.lacitysan.landfill.server.service.report.model.data;

/**
 * Represents a single entry on an instantaneous report.
 * @author Alvin Quach
 */
public class InstantaneousReportData extends SurfaceEmissionReportData {
	
	private String imeNumber;

	public String getImeNumber() {
		return imeNumber;
	}

	public void setImeNumber(String imeNumber) {
		this.imeNumber = imeNumber;
	}

}
