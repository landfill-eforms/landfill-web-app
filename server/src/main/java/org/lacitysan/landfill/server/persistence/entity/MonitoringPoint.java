package org.lacitysan.landfill.server.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.lib.enumeration.MonitoringPointType;
import org.lacitysan.landfill.lib.enumeration.Site;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name = "test.dbo.MonitoringPoints")
public class MonitoringPoint {

	@Id
	@Column(name="MonitoringPointPK")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name="MonitoringPointTypeFK")
	private MonitoringPointType monitoringPointType;

	@NotNull
	private String monitoringPointName;

	@Column(name="SiteFK")
	@Enumerated(EnumType.ORDINAL)
	private Site site;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MonitoringPointType getMonitoringPointType() {
		return monitoringPointType;
	}

	public void setMonitoringPointType(MonitoringPointType monitoringPointType) {
		this.monitoringPointType = monitoringPointType;
	}

	public String getMonitoringPointName() {
		return monitoringPointName;
	}

	public void setMonitoringPointName(String monitoringPointName) {
		this.monitoringPointName = monitoringPointName;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

}
