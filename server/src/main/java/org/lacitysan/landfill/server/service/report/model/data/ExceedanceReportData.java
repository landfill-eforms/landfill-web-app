package org.lacitysan.landfill.server.service.report.model.data;

/**
 * Represents a single entry on an exceedance report.
 * @author Alvin Quach
 */
public class ExceedanceReportData {
	
	private String date;
	private String landfill;
	private String type;
	private String identifier;
	private String location;
	private String repair;
	private String initial;
	private String recheck;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLandfill() {
		return landfill;
	}

	public void setLandfill(String landfill) {
		this.landfill = landfill;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRepair() {
		return repair;
	}

	public void setRepair(String repair) {
		this.repair = repair;
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
	
}
