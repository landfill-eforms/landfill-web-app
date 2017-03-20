package org.lacitysan.landfill.server.persistence.entity.instantaneous;

import java.util.HashSet;
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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.lacitysan.landfill.server.config.constant.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedInstantaneousData;
import org.lacitysan.landfill.server.persistence.enums.ImeNumberStatus;
import org.lacitysan.landfill.server.persistence.enums.MonitoringPoint;
import org.lacitysan.landfill.server.persistence.enums.Site;
import org.lacitysan.landfill.server.service.ImeService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name=ApplicationConstant.DATABASE_NAME + ".dbo.IMENumbers")
@JsonInclude(Include.NON_NULL)
public class ImeNumber implements Comparable<ImeNumber> {

	@Id
	@Column(name="IMENumberPK")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(name="SiteString")
	@Enumerated(EnumType.STRING)
	private Site site;
	
	@NotNull
	private Integer dateCode;
	
	@NotNull
	private Short sequence;
	
	@NotNull
	@Column(name="StatusString")
	@Enumerated(EnumType.STRING)
	private ImeNumberStatus status;
	
	@ElementCollection(targetClass=MonitoringPoint.class)
	@JoinTable(name="test.dbo.IMENumbersXRefMonitoringPoints", joinColumns=@JoinColumn(name="IMENumberFK"))
	@Column(name="MonitoringPointString")
	@Enumerated(EnumType.STRING)
	private Set<MonitoringPoint> monitoringPoints = new HashSet<>();
	
	@JsonIgnoreProperties({"imeNumbers", "warmspotData", "instrument", "inspector"})
	@ManyToMany(mappedBy="imeNumbers")
	private Set<InstantaneousData> instantaneousData = new HashSet<>();
	
	@JsonIgnoreProperties({"unverifiedDataSet", "imeNumbers", "warmspotData", "instrument"})
	@ManyToMany(mappedBy="imeNumbers")
	private Set<UnverifiedInstantaneousData> unverifiedInstantaneousData = new HashSet<>();
	
	@JsonIgnoreProperties({"imeNumber"})
	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy="imeNumber")
	private Set<ImeData> imeData = new HashSet<>();

	@Transient
	private String imeNumber;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDateCode() {
		return dateCode;
	}

	public void setDateCode(Integer dateCode) {
		this.dateCode = dateCode;
	}

	public Short getSequence() {
		return sequence;
	}

	public void setSequence(Short sequence) {
		this.sequence = sequence;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public ImeNumberStatus getStatus() {
		return status;
	}

	public void setStatus(ImeNumberStatus status) {
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
		return new ImeService().getStringFromImeNumber(this);
	}

	@Override
	public int compareTo(ImeNumber o) {
		if (this.site != o.getSite()) {
			return this.site.compareTo(o.getSite());
		}
		if (!this.dateCode.equals(o.getDateCode())) {
			return this.dateCode - o.getDateCode();
		}
		if (!this.sequence.equals(o.getSequence())) {
			return this.sequence - o.getSequence();
		}
		return 0;
	}

}
