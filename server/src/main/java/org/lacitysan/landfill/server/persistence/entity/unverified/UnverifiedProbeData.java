package org.lacitysan.landfill.server.persistence.entity.unverified;

import java.sql.Date;
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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.lacitysan.landfill.server.persistence.enums.location.MonitoringPoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.UnverifiedProbeData")
@AttributeOverride(name="id", column=@Column(name="UnverifiedProbePK"))
@JsonInclude(Include.NON_NULL)
public class UnverifiedProbeData extends AbstractUnverifiedData {
	
	@Column(name="MonitoringPointString")
	@Enumerated(EnumType.STRING)
	private MonitoringPoint monitoringPoint;
	
	@NotNull
	private Date date;
	
	@NotNull
	private Integer methaneLevel;
	
	@NotNull
	private Integer pressureLevel;
	
	@NotNull
	private String description;
	
	@JsonIgnoreProperties("instrumentType")
	@ManyToOne
	@JoinColumn(name="InstrumentFK")
	private Instrument instrument;
	
	@NotNull
	private Short barometricPressure;
	
	@NotNull
	private Boolean accessible;
	
	@JsonIgnoreProperties(value={"userGroups", "enabled", "lastLogin", "createdBy", "createdDate", "modifiedBy", "modifiedDate"}, allowSetters=true)
	@ManyToMany
	@JoinTable(name="dbo.UnverifiedProbeDataXRefInspectors", joinColumns=@JoinColumn(name="UnverifiedProbeFK"), inverseJoinColumns=@JoinColumn(name="InspectorFK"))
	private Set<User> inspectors = new HashSet<>();
	
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getMethaneLevel() {
		return methaneLevel;
	}

	public void setMethaneLevel(Integer methaneLevel) {
		this.methaneLevel = methaneLevel;
	}

	public Integer getPressureLevel() {
		return pressureLevel;
	}

	public void setPressureLevel(Integer pressureLevel) {
		this.pressureLevel = pressureLevel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getBarometricPressure() {
		return barometricPressure;
	}

	public void setBarometricPressure(Short barometricPressure) {
		this.barometricPressure = barometricPressure;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}

	public Boolean getAccessible() {
		return accessible;
	}

	public void setAccessible(Boolean accessible) {
		this.accessible = accessible;
	}

	public Set<User> getInspectors() {
		return inspectors;
	}

	public void setInspectors(Set<User> inspectors) {
		this.inspectors = inspectors;
	}

	public UnverifiedDataSet getUnverifiedDataSet() {
		return unverifiedDataSet;
	}

	public void setUnverifiedDataSet(UnverifiedDataSet unverifiedDataSet) {
		this.unverifiedDataSet = unverifiedDataSet;
	}
	
}
