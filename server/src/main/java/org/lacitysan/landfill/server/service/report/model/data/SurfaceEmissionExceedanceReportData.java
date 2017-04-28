package org.lacitysan.landfill.server.service.report.model.data;

/**
 * Represents a single surface emission exceedance entry on an exceedance report.
 * @author Alvin Quach
 */
public class SurfaceEmissionExceedanceReportData {
	
	private String discoveredDate;
	private String exceedanceNumber;
	private String monitoringPoints;
	private String repairDescription;
	private String initial;
	private String recheck;
	private String clearedDate;

	public String getDiscoveredDate() {
		return discoveredDate;
	}

	public void setDiscoveredDate(String discoveredDate) {
		this.discoveredDate = discoveredDate;
	}

	public String getExceedanceNumber() {
		return exceedanceNumber;
	}

	public void setExceedanceNumber(String exceedanceNumber) {
		this.exceedanceNumber = exceedanceNumber;
	}

	public String getMonitoringPoints() {
		return monitoringPoints;
	}

	public void setMonitoringPoints(String monitoringPoints) {
		this.monitoringPoints = monitoringPoints;
	}

	public String getRepairDescription() {
		return repairDescription;
	}

	public void setRepairDescription(String repairDescription) {
		this.repairDescription = repairDescription;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public String getRecheck() {
		return recheck;
	}

	public void setRecheck(String recheck) {
		this.recheck = recheck;
	}

	public String getClearedDate() {
		return clearedDate;
	}

	public void setClearedDate(String clearedDate) {
		this.clearedDate = clearedDate;
	}
	
}
