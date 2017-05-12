package org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.SurfaceEmissionExceedanceNumber;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedInstantaneousData;
import org.lacitysan.landfill.server.persistence.enums.location.MonitoringPoint;
import org.lacitysan.landfill.server.service.surfaceemission.instantaneous.ImeNumberService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.IMENumbers")
@AttributeOverride(name="id", column=@Column(name="IMENumberPK"))
@JsonInclude(Include.NON_NULL)
public class ImeNumber extends SurfaceEmissionExceedanceNumber {
	
	@ElementCollection(targetClass=MonitoringPoint.class)
	@JoinTable(name="dbo.IMENumbersXRefMonitoringPoints", joinColumns=@JoinColumn(name="IMENumberFK"))
	@Column(name="MonitoringPointString")
	@Enumerated(EnumType.STRING)
	private Set<MonitoringPoint> monitoringPoints = new HashSet<>();
	
	@JsonIgnoreProperties(value={"imeNumbers", "warmspotData", "instrument", "inspector"}, allowSetters=true)
	@ManyToMany(mappedBy="imeNumbers")
	private Set<InstantaneousData> instantaneousData = new HashSet<>();
	
	@JsonIgnoreProperties(value={"imeNumbers", "unverifiedWarmspotData", "instrument"}, allowSetters=true)
	@ManyToMany(mappedBy="imeNumbers")
	private Set<UnverifiedInstantaneousData> unverifiedInstantaneousData = new HashSet<>();
	
	@JsonIgnoreProperties("imeNumber")
	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy="imeNumber")
	private Set<ImeData> imeData = new TreeSet<>();

	@Transient
	private String imeNumber;
	
	public Set<MonitoringPoint> getMonitoringPoints() {
		return monitoringPoints;
	}

	public void setMonitoringPoints(Set<MonitoringPoint> monitoringPoints) {
		this.monitoringPoints = monitoringPoints;
	}

	public Set<InstantaneousData> getInstantaneousData() {
		return instantaneousData;
	}

	public void setInstantaneousData(Set<InstantaneousData> instantaneousData) {
		this.instantaneousData = instantaneousData;
	}
	
	public Set<UnverifiedInstantaneousData> getUnverifiedInstantaneousData() {
		return unverifiedInstantaneousData;
	}

	public void setUnverifiedInstantaneousData(Set<UnverifiedInstantaneousData> unverifiedInstantaneousData) {
		this.unverifiedInstantaneousData = unverifiedInstantaneousData;
	}

	public Set<ImeData> getImeData() {
		return imeData;
	}

	public void setImeData(Set<ImeData> imeData) {
		this.imeData = imeData;
	}

	public String getImeNumber() {
		return this.toString();
	}

	public void setImeNumber(String imeNumber) {
		this.imeNumber = this.toString();
	}

	@Override
	public String toString() {
		return new ImeNumberService().generateStringFromImeNumber(this);
	}

}
