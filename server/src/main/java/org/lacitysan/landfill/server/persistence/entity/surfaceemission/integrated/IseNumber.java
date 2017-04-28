package org.lacitysan.landfill.server.persistence.entity.surfaceemission.integrated;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.SurfaceEmissionExceedanceNumber;
import org.lacitysan.landfill.server.persistence.enums.location.MonitoringPoint;
import org.lacitysan.landfill.server.service.surfaceemission.integrated.IseNumberService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.ISENumbers")
@AttributeOverride(name="id", column=@Column(name="ISENumberPK"))
@JsonInclude(Include.NON_NULL)
public class IseNumber extends SurfaceEmissionExceedanceNumber {
	
	@NotNull
	@Column(name="MonitoringPointString")
	@Enumerated(EnumType.STRING)
	private MonitoringPoint monitoringPoint;
	
	@JsonIgnoreProperties("iseNumber")
	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy="iseNumber")
	private Set<IseData> iseData = new TreeSet<>();

	@Transient
	private String iseNumber;

	public MonitoringPoint getMonitoringPoint() {
		return monitoringPoint;
	}

	public void setMonitoringPoint(MonitoringPoint monitoringPoint) {
		this.monitoringPoint = monitoringPoint;
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
		return new IseNumberService().generateStringFromIseNumber(this);
	}

}
