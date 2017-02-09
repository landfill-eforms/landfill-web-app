package org.lacitysan.landfill.server.persistence.entity.instantaneous;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.lacitysan.landfill.server.model.MonitoringPoint;
import org.lacitysan.landfill.server.model.Site;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name=ApplicationProperty.DATABASE_NAME + ".dbo.IMENumbers")
public class IMENumber {

	@Id
	@Column(name="IMENumberPK")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="SiteOrdinal")
	@Enumerated(EnumType.ORDINAL)
	private Site site;
	
	@NotNull
	private Timestamp discoveryDate;
	
	@NotNull
	private Short series;
	
	@NotNull
	private Boolean unverified;
	
	@ElementCollection(targetClass=MonitoringPoint.class)
	@JoinTable(name="test.dbo.IMENumbersXRefMonitoringPoints", joinColumns=@JoinColumn(name="IMENumberFK"))
	@Enumerated(EnumType.ORDINAL)
	private Set<MonitoringPoint> monitoringPoints;
	
	@JsonIgnoreProperties({"imeNumber"})
	@OneToMany(mappedBy="imeNumber")
	private Set<InstantaneousData> instantaneousData;
	
	@JsonIgnoreProperties({"imeNumber"})
	@OneToMany(mappedBy="imeNumber")
	private Set<IMEData> imeData;
	
	@JsonIgnoreProperties({"imeNumber"})
	@OneToMany(mappedBy="imeNumber")
	private Set<IMERepairData> imeRepairData;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getDiscoveryDate() {
		return discoveryDate;
	}

	public void setDiscoveryDate(Timestamp discoveryDate) {
		this.discoveryDate = discoveryDate;
	}

	public Short getSeries() {
		return series;
	}

	public void setSeries(Short series) {
		this.series = series;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Boolean getUnverified() {
		return unverified;
	}

	public void setUnverified(Boolean unverified) {
		this.unverified = unverified;
	}

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

	public Set<IMEData> getImeData() {
		return imeData;
	}

	public void setImeData(Set<IMEData> imeData) {
		this.imeData = imeData;
	}
	
	public Set<IMERepairData> getImeRepairData() {
		return imeRepairData;
	}

	public void setImeRepairData(Set<IMERepairData> imeRepairData) {
		this.imeRepairData = imeRepairData;
	}

	@Override
	public String toString() {
		Calendar date = new GregorianCalendar();
		date.setTime(this.discoveryDate);
		return this.site.getShortName() + 
				"-" + 
				(date.get(Calendar.MONTH) + 1) +
				(date.get(Calendar.DAY_OF_MONTH)) +
				(date.get(Calendar.YEAR) % 2000) +
				"-" + 
				(this.series < 10 ? "0" + this.series : this.series);
	}
	
}
