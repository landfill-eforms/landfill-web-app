package org.lacitysan.landfill.server.persistence.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;

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
	private String imeNumber;
	
	@JsonIgnoreProperties({"imeNumbers"})
	@ManyToMany
	@JoinTable(name="test.dbo.IMENumbersXRefMonitoringPoints", joinColumns=@JoinColumn(name="IMENumberFK"), inverseJoinColumns=@JoinColumn(name="MonitoringPointFK"))
	private Set<MonitoringPoint> monitoringPoints;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImeNumber() {
		return imeNumber;
	}

	public void setImeNumber(String imeNumber) {
		this.imeNumber = imeNumber;
	}

	public Set<MonitoringPoint> getMonitoringPoints() {
		return monitoringPoints;
	}

	public void setMonitoringPoints(Set<MonitoringPoint> monitoringPoints) {
		this.monitoringPoints = monitoringPoints;
	}
	
}
