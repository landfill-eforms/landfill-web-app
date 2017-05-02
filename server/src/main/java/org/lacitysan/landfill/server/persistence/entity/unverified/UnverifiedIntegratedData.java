package org.lacitysan.landfill.server.persistence.entity.unverified;

import java.sql.Timestamp;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.lacitysan.landfill.server.persistence.entity.AbstractEntity;
import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;
import org.lacitysan.landfill.server.persistence.enums.location.MonitoringPoint;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.UnverifiedIntegratedData")
@AttributeOverride(name="id", column=@Column(name="UnverifiedIntegratedPK"))
@JsonInclude(Include.NON_NULL)
public class UnverifiedIntegratedData extends AbstractEntity {
	
	@NotNull
	@Column(name="MonitoringPointString")
	@Enumerated(EnumType.STRING)
	private MonitoringPoint monitoringPoint;
	
	@ManyToOne
	@JoinColumn(name="InstrumentFK")
	private Instrument instrument;
	
	@NotNull
	private String sampleId;
	
	@NotNull
	private Short bagNumber;
	
	@NotNull
	private Short volume;
	
	@NotNull
	private Short barometricPressure;
	
	@NotNull
	private Integer methaneLevel;
	
	@NotNull
	private Timestamp startTime;
	
	@NotNull
	private Timestamp endTime;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="UnverifiedDataSetFK", nullable=false)
	@Cascade(CascadeType.ALL)
	private UnverifiedDataSet unverifiedDataSet;

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

	public String getSampleId() {
		return sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	public Short getBagNumber() {
		return bagNumber;
	}

	public void setBagNumber(Short bagNumber) {
		this.bagNumber = bagNumber;
	}

	public Short getVolume() {
		return volume;
	}

	public void setVolume(Short volume) {
		this.volume = volume;
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

	public UnverifiedDataSet getUnverifiedDataSet() {
		return unverifiedDataSet;
	}

	public void setUnverifiedDataSet(UnverifiedDataSet unverifiedDataSet) {
		this.unverifiedDataSet = unverifiedDataSet;
	}
	
}
