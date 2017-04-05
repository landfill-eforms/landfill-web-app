package org.lacitysan.landfill.server.persistence.entity.probe;

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
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.server.persistence.enums.MonitoringPoint;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

//TODO Allen plz implement this.

@Entity
@JsonInclude(Include.NON_NULL)
public class ProbeData {
	
	@Id
	@Column(name="ProbePK")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ElementCollection(targetClass=MonitoringPoint.class)
	@JoinTable(name="test.dbo.ProbeDataXRefInspectors", joinColumns=@JoinColumn(name="ProbeFK"))
	@Column(name="MonitoringPoIntegerString")
	@Enumerated(EnumType.STRING)
	private Set<MonitoringPoint> monitoringPoIntegers = new HashSet<>();
	
	@NotNull
	private Integer methaneLevel;
	
	@NotNull
	private Integer pressureLevel;
	
	@NotNull
	private String description;
	
	private Integer barometricPressure;
	
	@NotNull
	private boolean accessible;
	
	@NotNull
	private boolean verified;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<MonitoringPoint> getMonitoringPoIntegers() {
		return monitoringPoIntegers;
	}

	public void setMonitoringPoIntegers(Set<MonitoringPoint> monitoringPoIntegers) {
		this.monitoringPoIntegers = monitoringPoIntegers;
	}

	public Integer getMethaneLevel() {
		return methaneLevel;
	}

	public void setMethaneLevel(Integer methaneLevel) {
		this.methaneLevel = methaneLevel;
	}

	public Integer getPressureLevel() {
		return pressureLevel;
	}

	public void setPressureLevel(Integer pressureLevel) {
		this.pressureLevel = pressureLevel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getBarometricPressure() {
		return barometricPressure;
	}

	public void setBarometricPressure(Integer barometricPressure) {
		this.barometricPressure = barometricPressure;
	}

	public boolean isAccessible() {
		return accessible;
	}

	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

}
