package org.lacitysan.landfill.server.persistence.entity.unverified;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
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
@JsonInclude(Include.NON_NULL)
public class UnverifiedWarmspotData {
	
	@Id
	@Column(name="UnverifiedWarmspotPK")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(name="MonitoringPointString")
	@Enumerated(EnumType.STRING)
	private MonitoringPoint monitoringPoint;
	
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
	
	@JsonIgnoreProperties(value={"unverifiedWarmspotData", "imeNumbers", "inspector", "instrument", "unverifiedDataSet"},allowSetters=true)
	@OneToOne(mappedBy="unverifiedWarmspotData")
	private UnverifiedInstantaneousData unverifiedInstantaneousData;
	
	@JsonIgnoreProperties("unverifiedInstantaneousData")
	@NotNull
	@ManyToOne
	@JoinColumn(name="UnverifiedDataSetFK", nullable=false)
	@Cascade(CascadeType.ALL)
	private UnverifiedDataSet unverifiedDataSet;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public UnverifiedInstantaneousData getUnverifiedInstantaneousData() {
		return unverifiedInstantaneousData;
	}

	public void setUnverifiedInstantaneousData(UnverifiedInstantaneousData unverifiedInstantaneousData) {
		this.unverifiedInstantaneousData = unverifiedInstantaneousData;
	}

	public UnverifiedDataSet getUnverifiedDataSet() {
		return unverifiedDataSet;
	}

	public void setUnverifiedDataSet(UnverifiedDataSet unverifiedDataSet) {
		this.unverifiedDataSet = unverifiedDataSet;
	}

}
