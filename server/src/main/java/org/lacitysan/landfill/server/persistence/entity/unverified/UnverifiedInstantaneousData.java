package org.lacitysan.landfill.server.persistence.entity.unverified;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.server.persistence.entity.AbstractEntity;
import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.persistence.enums.location.MonitoringPoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.UnverifiedInstantaneousData")
@AttributeOverride(name="id", column=@Column(name="UnverifiedInstantaneousPK"))
@JsonInclude(Include.NON_NULL)
public class UnverifiedInstantaneousData extends AbstractEntity {
	
	@NotNull
	@Column(name="MonitoringPointString")
	@Enumerated(EnumType.STRING)
	private MonitoringPoint monitoringPoint;
	
	@ManyToOne
	@JoinColumn(name="InstrumentFK")
	private Instrument instrument;

	private Short barometricPressure;
	
	@NotNull
	private Integer methaneLevel;	
	
	@NotNull
	private Timestamp startTime;
	
	@NotNull
	private Timestamp endTime;
	
	@JsonIgnoreProperties(value={"unverifiedDataSet", "unverifiedInstantaneousData", "instantaneousData", "imeRepairData"}, allowSetters=true)
	@ManyToMany
	@JoinTable(name="dbo.UnverifiedInstantaneousDataXRefIMENumbers", joinColumns=@JoinColumn(name="UnverifiedInstantaneousFK"), inverseJoinColumns=@JoinColumn(name="IMENumberFK"))
	private Set<ImeNumber> imeNumbers = new HashSet<>();
	
	@JsonIgnoreProperties(value={
			"unverifiedInstantaneousData", 
			"unverifiedWarmspotData", 
			"imeNumbers", 
			"unverifiedIntegratedData", 
			"iseNumbers", 
			"unverifiedProbeData", 
			"probeExceedances", 
			"inspector", 
			"createdBy",
			"createdDate",
			"modifiedBy",
			"modifiedDate"
	}, allowSetters=true)
	@NotNull
	@ManyToOne
	@JoinColumn(name="UnverifiedDataSetFK", nullable=false)
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

	public Set<ImeNumber> getImeNumbers() {
		return imeNumbers;
	}

	public void setImeNumbers(Set<ImeNumber> imeNumbers) {
		this.imeNumbers = imeNumbers;
	}

	public UnverifiedDataSet getUnverifiedDataSet() {
		return unverifiedDataSet;
	}

	public void setUnverifiedDataSet(UnverifiedDataSet unverifiedDataSet) {
		this.unverifiedDataSet = unverifiedDataSet;
	}
	
}
