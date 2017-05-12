package org.lacitysan.landfill.server.persistence.entity.surfaceemission;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.server.persistence.entity.AbstractEntity;
import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.lacitysan.landfill.server.persistence.enums.location.MonitoringPoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Alvin Quach
 */
@MappedSuperclass
public abstract class SurfaceEmissionData extends AbstractEntity {
		
	@Column(name="MonitoringPointString")
	@Enumerated(EnumType.STRING)
	private MonitoringPoint monitoringPoint;
	
	@JsonIgnoreProperties("instrumentType")
	@ManyToOne
	@JoinColumn(name="InstrumentFK")
	private Instrument instrument;
	
	@JsonIgnoreProperties({"userGroups", "enabled", "lastLogin", "createdBy", "createdDate", "modifiedBy", "modifiedDate"})
	@ManyToOne
	@JoinColumn(name="InspectorFK")
	private User inspector;
	
	@NotNull
	private Short  barometricPressure;
	
	@NotNull
	private Integer methaneLevel;	
	
	@NotNull
	private Timestamp startTime;
	
	@NotNull
	private Timestamp endTime;

	public MonitoringPoint getMonitoringPoint() {
		return monitoringPoint;
	}

	public void setMonitoringPoint(MonitoringPoint monitoringPoint) {
		this.monitoringPoint = monitoringPoint;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}

	public User getInspector() {
		return inspector;
	}

	public void setInspector(User inspector) {
		this.inspector = inspector;
	}

	public Short getBarometricPressure() {
		return barometricPressure;
	}

	public void setBarometricPressure(Short barometricPressure) {
		this.barometricPressure = barometricPressure;
	}

	public Integer getMethaneLevel() {
		return methaneLevel;
	}

	public void setMethaneLevel(Integer methaneLevel) {
		this.methaneLevel = methaneLevel;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

}
