package org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous;

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

import org.lacitysan.landfill.server.persistence.entity.AbstractEntity;
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
@Table(name="dbo.WarmspotData")
@AttributeOverride(name="id", column=@Column(name="WarmspotPK"))
@JsonInclude(Include.NON_NULL)
public class WarmspotData extends AbstractEntity {
	
	/**
	 * The minimum methane level required in ppm*100 for an instantaneous reading to be considered a warmspot.
	 * To get the level in ppm, divide this number by 100.
	 */
	public static final int MININIMUM_READING = 20000;
	
	/**
	 * The maximum methane level in ppm*100 for which instantaneous reading will still be considered a warmspot.
	 * Any higher than this will be considered an hotspot.
	 * To get the level in ppm, divide this number by 100.
	 */
	public static final int MAXNIMUM_READING = 50000;
	
	@ElementCollection(targetClass=MonitoringPoint.class)
	@JoinTable(name="dbo.WarmspotDataXRefMonitoringPoints", joinColumns=@JoinColumn(name="WarmspotFK"))
	@Column(name="MonitoringPointString")
	@Enumerated(EnumType.STRING)
	private Set<MonitoringPoint> monitoringPoints = new HashSet<>();
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="InstrumentFK")
	private Instrument instrument;
	
	@JsonIgnoreProperties({"userGroups"})
	@NotNull
	@ManyToOne
	@JoinColumn(name="InspectorFK")
	private User inspector;
	
	@NotNull
	private Integer methaneLevel;	
	
	@NotNull
	private Date date;
	
	@NotNull
	private String description;
	
	@NotNull
	private String size;
	
	public static int getMininimumReading() {
		return MININIMUM_READING;
	}

	public static int getMaxnimumReading() {
		return MAXNIMUM_READING;
	}

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

	public User getInspector() {
		return inspector;
	}

	public void setInspector(User inspector) {
		this.inspector = inspector;
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

}
