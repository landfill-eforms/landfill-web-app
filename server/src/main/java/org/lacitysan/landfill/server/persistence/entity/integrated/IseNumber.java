package org.lacitysan.landfill.server.persistence.entity.integrated;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.lacitysan.landfill.server.persistence.enums.exceedance.ExceedanceStatus;
import org.lacitysan.landfill.server.persistence.enums.location.MonitoringPoint;
import org.lacitysan.landfill.server.persistence.enums.location.Site;
import org.lacitysan.landfill.server.service.integrated.IseService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.ISENumbers")
@JsonInclude(Include.NON_NULL)
public class IseNumber implements Comparable<IseNumber> {
	
	@Id
	@Column(name="ISENumberPK")
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
	private ExceedanceStatus status;
	
	@ElementCollection(targetClass=MonitoringPoint.class)
	@JoinTable(name="dbo.ISENumbersXRefMonitoringPoints", joinColumns=@JoinColumn(name="ISENumberFK"))
	@Column(name="MonitoringPointString")
	@Enumerated(EnumType.STRING)
	private Set<MonitoringPoint> monitoringPoints = new HashSet<>();
	
	@JsonIgnoreProperties({"iseNumber"})
	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy="iseNumber")
	private Set<IseData> iseData = new HashSet<>();

	@Transient
	private String iseNumber;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
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

	public ExceedanceStatus getStatus() {
		return status;
	}

	public void setStatus(ExceedanceStatus status) {
		this.status = status;
	}

	public Set<MonitoringPoint> getMonitoringPoints() {
		return monitoringPoints;
	}

	public void setMonitoringPoints(Set<MonitoringPoint> monitoringPoints) {
		this.monitoringPoints = monitoringPoints;
	}

	public Set<IseData> getIseData() {
		return iseData;
	}

	public void setIseData(Set<IseData> iseData) {
		this.iseData = iseData;
	}

	public String getIseNumber() {
		return this.toString();
	}

	public void setIseNumber(String iseNumber) {
		this.iseNumber = this.toString();
	}
	
	@Override
	public String toString() {
		return new IseService().getStringFromIseNumber(this);
	}

	@Override
	public int compareTo(IseNumber o) {
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
