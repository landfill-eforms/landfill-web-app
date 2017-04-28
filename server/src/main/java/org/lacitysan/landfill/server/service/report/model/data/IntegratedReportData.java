package org.lacitysan.landfill.server.service.report.model.data;

/**
 * Represents a single entry on an integrated report.
 * @author Alvin Quach
 */
public class IntegratedReportData extends SurfaceEmissionReportData {
	
	private String bagNumber;
	
	private String volume;
	
	private String sampleId;

	public String getBagNumber() {
		return bagNumber;
	}

	public void setBagNumber(String bagNumber) {
		this.bagNumber = bagNumber;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getSampleId() {
		return sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

}
