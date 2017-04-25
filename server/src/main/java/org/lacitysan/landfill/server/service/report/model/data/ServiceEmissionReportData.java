package org.lacitysan.landfill.server.service.report.model.data;

/**
 * @author Alvin Quach
 */
public abstract class ServiceEmissionReportData {
	
	private String date;
	private String barometricPressure;
	private String inspector;
	private String monitoringPoint;
	private String startTime;
	private String endTime;
	private String instrument;
	private String methaneLevel;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBarometricPressure() {
		return barometricPressure;
	}

	public void setBarometricPressure(String barometricPressure) {
		this.barometricPressure = barometricPressure;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public String getMonitoringPoint() {
		return monitoringPoint;
	}

	public void setMonitoringPoint(String monitoringPoint) {
		this.monitoringPoint = monitoringPoint;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public String getMethaneLevel() {
		return methaneLevel;
	}

	public void setMethaneLevel(String methaneLevel) {
		this.methaneLevel = methaneLevel;
	}

}
