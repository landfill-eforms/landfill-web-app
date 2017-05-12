package org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous;

import java.sql.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
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
	
	@NotNull
	@Column(name="MonitoringPointString")
	@Enumerated(EnumType.STRING)
	private MonitoringPoint monitoringPoint;
	
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
