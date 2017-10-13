package org.lacitysan.landfill.server.service.report.model.data;

import java.util.Set;

public class WarmspotReportData {
	private String monitoringPoint;
	private String instrument;
	private String inspector;
	private String methane;
	private String date;
	private String description;
	private String size;
	
	public String getMonitoringPoint() {
		return monitoringPoint;
	}
	public void setMonitoringPoint(String monitoringPoint) {
		this.monitoringPoint = monitoringPoint;
	}
	public String getInstrument() {
		return instrument;
	}
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	public String getInspector() {
		return inspector;
	}
	public void setInspector(String inspector) {
		this.inspector = inspector;
	}
	public String getMethane() {
		return methane;
	}
	public void setMethane(String methane) {
		this.methane = methane;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	
}
