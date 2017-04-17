package org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AssociationOverride;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.ServiceEmissionsExceedanceNumber;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedInstantaneousData;
import org.lacitysan.landfill.server.service.serviceemission.instantaneous.ImeService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.IMENumbers")
@AttributeOverride(name="id", column=@Column(name="IMENumberPK"))
@AssociationOverride(name="monitoringPoints", joinTable=@JoinTable(name="dbo.IMENumbersXRefMonitoringPoints", joinColumns=@JoinColumn(name="IMENumberFK")))
@JsonInclude(Include.NON_NULL)
public class ImeNumber extends ServiceEmissionsExceedanceNumber {
	
	@JsonIgnoreProperties(value={"imeNumbers", "warmspotData", "instrument", "inspector"}, allowSetters=true)
	@ManyToMany(mappedBy="imeNumbers")
	private Set<InstantaneousData> instantaneousData = new HashSet<>();
	
	@JsonIgnoreProperties(value={"unverifiedDataSet", "imeNumbers", "warmspotData", "instrument"}, allowSetters=true)
	@ManyToMany(mappedBy="imeNumbers")
	private Set<UnverifiedInstantaneousData> unverifiedInstantaneousData = new HashSet<>();
	
	@JsonIgnoreProperties("imeNumber")
	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy="imeNumber")
	private Set<ImeData> imeData = new HashSet<>();

	@Transient
	private String imeNumber;
	
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

}
