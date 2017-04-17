package org.lacitysan.landfill.server.persistence.entity.exceedance;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.server.persistence.enums.exceedance.ExceedanceStatus;
import org.lacitysan.landfill.server.persistence.enums.location.MonitoringPoint;
import org.lacitysan.landfill.server.persistence.enums.location.Site;

/**
 * An alphanumeric identifier for a service emissions exceedance, such as an IME or ISE.
 * The format of the identifier is AAyyMM-BB, where AA is the short name of the site, yyMM is the date format, and BB is the sequence number.
 * @author Alvin Quach
 */
@MappedSuperclass
public abstract class ServiceEmissionsExceedanceNumber implements Comparable<ServiceEmissionsExceedanceNumber> {
	
	@Id
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
	@Column(name="MonitoringPointString")
	@Enumerated(EnumType.STRING)
	private Set<MonitoringPoint> monitoringPoints = new HashSet<>();

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
	
	@Override
	abstract public String toString();

	@Override
	public int compareTo(ServiceEmissionsExceedanceNumber o) {
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
