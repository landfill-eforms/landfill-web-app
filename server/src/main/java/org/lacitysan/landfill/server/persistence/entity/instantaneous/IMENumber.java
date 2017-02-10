package org.lacitysan.landfill.server.persistence.entity.instantaneous;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.lacitysan.landfill.server.model.MonitoringPoint;
import org.lacitysan.landfill.server.model.Site;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedInstantaneousData;

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
	
	@NotNull
	@Column(name="SiteOrdinal")
	@Enumerated(EnumType.ORDINAL)
	private Site site;
	
	@NotNull
	private Timestamp discoveryDate;
	
	@NotNull
	private Short series;
	
	@NotNull
	@Column(name="StatusOrdinal")
	@Enumerated(EnumType.ORDINAL)
	private IMENumberStatus status;
	
	@ElementCollection(targetClass=MonitoringPoint.class)
	@JoinTable(name="test.dbo.IMENumbersXRefMonitoringPoints", joinColumns=@JoinColumn(name="IMENumberFK"))
	@Column(name="MonitoringPointOrdinal")
	@Enumerated(EnumType.ORDINAL)
	private Set<MonitoringPoint> monitoringPoints;
	
	@JsonIgnoreProperties({"imeNumber", "instrument", "inspector"})
	@OneToMany(mappedBy="imeNumber")
	private Set<InstantaneousData> instantaneousData;
	
	@JsonIgnoreProperties({"unverifiedDataSet", "imeNumber", "instrument"})
	@OneToMany(mappedBy="imeNumber")
	private Set<UnverifiedInstantaneousData> unverifiedInstantaneousData;
	
	@JsonIgnoreProperties({"imeNumber"})
	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy="imeNumber")
	private Set<IMEData> imeData;
	
	@JsonIgnoreProperties({"imeNumber"})
	@OneToMany(mappedBy="imeNumber")
	private Set<IMERepairData> imeRepairData;

	@Transient
	private String imeNumber;
	
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

	public IMENumberStatus getStatus() {
		return status;
	}

	public void setStatus(IMENumberStatus status) {
		this.status = status;
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
	
	public Set<UnverifiedInstantaneousData> getUnverifiedInstantaneousData() {
		return unverifiedInstantaneousData;
	}

	public void setUnverifiedInstantaneousData(Set<UnverifiedInstantaneousData> unverifiedInstantaneousData) {
		this.unverifiedInstantaneousData = unverifiedInstantaneousData;
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

	public String getImeNumber() {
		return this.toString();
	}

	public void setImeNumber(String imeNumber) {
		this.imeNumber = this.toString();
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyy");
		return this.site.getShortName() + "-" + dateFormat.format(this.discoveryDate) + "-" +	(this.series < 10 ? "0" + this.series : this.series);
	}
	
	public enum IMENumberStatus {
		UNVERIFIED,
		ACTIVE,
		CLOSED
	}
	
}
