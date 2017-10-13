package org.lacitysan.landfill.server.persistence.entity.unverified;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;
import org.lacitysan.landfill.server.persistence.enums.location.MonitoringPoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.UnverifiedWarmspotData")
@AttributeOverride(name="id", column=@Column(name="UnverifiedWarmspotPK"))
@JsonInclude(Include.NON_NULL)
public class UnverifiedWarmspotData extends AbstractUnverifiedData {
	
	@ElementCollection(targetClass=MonitoringPoint.class)
	@JoinTable(name="dbo.UnverifiedWarmspotDataXRefMonitoringPoints", joinColumns=@JoinColumn(name="UnverifiedWarmspotFK"))
	@Column(name="MonitoringPointString")
	@Enumerated(EnumType.STRING)
	private Set<MonitoringPoint> monitoringPoints = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name="InstrumentFK")
	private Instrument instrument;
	
	@NotNull
	private Integer methaneLevel;	
	
	@NotNull
	private Date date;
	
	@NotNull
	private String description;
	
	@NotNull
	private String size;
	
	@JsonIgnoreProperties("unverifiedInstantaneousData")
	@NotNull
	@ManyToOne
	@JoinColumn(name="UnverifiedDataSetFK", nullable=false)
//	@Cascade(CascadeType.ALL)
	private UnverifiedDataSet unverifiedDataSet;

	public Set<MonitoringPoint> getMonitoringPoints() {
		return monitoringPoints;
	}

	public void setMonitoringPoints(Set<MonitoringPoint> monitoringPoints) {
		this.monitoringPoints = monitoringPoints;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}

	public Integer getMethaneLevel() {
		return methaneLevel;
	}

	public void setMethaneLevel(Integer methaneLevel) {
		this.methaneLevel = methaneLevel;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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

	public UnverifiedDataSet getUnverifiedDataSet() {
		return unverifiedDataSet;
	}

	public void setUnverifiedDataSet(UnverifiedDataSet unverifiedDataSet) {
		this.unverifiedDataSet = unverifiedDataSet;
	}

}
