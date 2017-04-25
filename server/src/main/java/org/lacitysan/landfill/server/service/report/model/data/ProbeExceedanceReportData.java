package org.lacitysan.landfill.server.service.report.model.data;

/**
 * Represents a single perimeter probe exceedance entry on an exceedance report.
 * @author Alvin Quach
 */
public class ProbeExceedanceReportData {
	
	private String discoveredDate;
	private String probeId;
	private String depth;
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

	public String getProbeId() {
		return probeId;
	}

	public void setProbeId(String probeId) {
		this.probeId = probeId;
	}

	public String getDepth() {
		return depth;
	}

	public void setDepth(String depth) {
		this.depth = depth;
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
