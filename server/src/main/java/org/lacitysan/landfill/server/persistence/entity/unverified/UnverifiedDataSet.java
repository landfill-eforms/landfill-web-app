package org.lacitysan.landfill.server.persistence.entity.unverified;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.lacitysan.landfill.server.persistence.entity.AbstractEntity;
import org.lacitysan.landfill.server.persistence.entity.probe.ProbeExceedance;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.integrated.IseNumber;
import org.lacitysan.landfill.server.persistence.entity.system.Trackable;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.lacitysan.landfill.server.persistence.enums.location.Site;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.UnverifiedDataSets")
@AttributeOverride(name="id", column=@Column(name="UnverifiedDataSetPK"))
@JsonInclude(Include.NON_NULL)
public class UnverifiedDataSet extends AbstractEntity implements Trackable {
	
	private String filename;
	
	@JsonIgnoreProperties({"userGroups", "enabled"})
	@ManyToOne
	@JoinColumn(name="InspectorFK")
	private User inspector;
	
	@Column(name="SiteString")
	@Enumerated(EnumType.STRING)
	private Site site;

	@JsonIgnoreProperties("unverifiedDataSet")
	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy="unverifiedDataSet")
	private Set<UnverifiedInstantaneousData> unverifiedInstantaneousData = new HashSet<>();
	
	@JsonIgnoreProperties(value="unverifiedDataSet",allowSetters=true)
	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy="unverifiedDataSet")
	private Set<UnverifiedWarmspotData> unverifiedWarmspotData = new HashSet<>();
	
	@JsonIgnoreProperties({"unverifiedDataSet", "instantaneousData"})
	@OneToMany(mappedBy="unverifiedDataSet")
	@Cascade(CascadeType.ALL)
	private Set<ImeNumber> imeNumbers = new TreeSet<>();
	
	@JsonIgnoreProperties("unverifiedDataSet")
	@OneToMany(mappedBy="unverifiedDataSet")
	@Cascade(CascadeType.ALL)
	private Set<UnverifiedIntegratedData> unverifiedIntegratedData = new HashSet<>();
	
	@JsonIgnoreProperties({"unverifiedDataSet"})
	@OneToMany(mappedBy="unverifiedDataSet")
	@Cascade(CascadeType.ALL)
	private Set<IseNumber> iseNumbers = new TreeSet<>();
	
	@JsonIgnoreProperties("unverifiedDataSet")
	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy="unverifiedDataSet")
	private Set<UnverifiedProbeData> unverifiedProbeData = new HashSet<>();
	
	@Transient
	private Set<ProbeExceedance> probeExceedances = new HashSet<>();
	
	@Transient
	private Map<String, List<String>> errors = new HashMap<>();

	// TODO Add other data types.
	
	@JsonIgnoreProperties(value={"userGroups", "enabled"}, allowSetters=true)
	@ManyToOne
	@JoinColumn(name="UploadedByFK")
	private User createdBy;
	
	@Column(name="UploadedDate")
	private Timestamp createdDate;
	
	@JsonIgnoreProperties(value={"userGroups", "enabled"}, allowSetters=true)
	@ManyToOne
	@JoinColumn(name="ModifiedByFK")
	private User modifiedBy;
	
	private Timestamp modifiedDate;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public User getInspector() {
		return inspector;
	}

	public void setInspector(User inspector) {
		this.inspector = inspector;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Set<UnverifiedInstantaneousData> getUnverifiedInstantaneousData() {
		return unverifiedInstantaneousData;
	}

	public void setUnverifiedInstantaneousData(Set<UnverifiedInstantaneousData> unverifiedInstantaneousData) {
		this.unverifiedInstantaneousData = unverifiedInstantaneousData;
	}

	public Set<UnverifiedWarmspotData> getUnverifiedWarmspotData() {
		return unverifiedWarmspotData;
	}

	public void setUnverifiedWarmspotData(Set<UnverifiedWarmspotData> unverifiedWarmspotData) {
		this.unverifiedWarmspotData = unverifiedWarmspotData;
	}

	public Set<ImeNumber> getImeNumbers() {
		return imeNumbers;
	}

	public void setImeNumbers(Set<ImeNumber> imeNumbers) {
		this.imeNumbers = imeNumbers;
	}

	public Set<UnverifiedIntegratedData> getUnverifiedIntegratedData() {
		return unverifiedIntegratedData;
	}

	public void setUnverifiedIntegratedData(Set<UnverifiedIntegratedData> unverifiedIntegratedData) {
		this.unverifiedIntegratedData = unverifiedIntegratedData;
	}

	public Set<IseNumber> getIseNumbers() {
		return iseNumbers;
	}

	public void setIseNumbers(Set<IseNumber> iseNumbers) {
		this.iseNumbers = iseNumbers;
	}

	public Set<UnverifiedProbeData> getUnverifiedProbeData() {
		return unverifiedProbeData;
	}

	public void setUnverifiedProbeData(Set<UnverifiedProbeData> unverifiedProbeData) {
		this.unverifiedProbeData = unverifiedProbeData;
	}

	public Set<ProbeExceedance> getProbeExceedances() {
		return probeExceedances;
	}

	public void setProbeExceedances(Set<ProbeExceedance> probeExceedances) {
		this.probeExceedances = probeExceedances;
	}

	public Map<String, List<String>> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, List<String>> errors) {
		this.errors = errors;
	}
	
	@Override
	public User getCreatedBy() {
		return createdBy;
	}

	@Override
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	@Override
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public User getModifiedBy() {
		return modifiedBy;
	}

	@Override
	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Override
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	@Override
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@JsonIgnore
	public boolean isEmpty() {
		return
				unverifiedInstantaneousData.isEmpty()
				&&
				unverifiedWarmspotData.isEmpty()
				&&
				imeNumbers.isEmpty()
				&&
				unverifiedIntegratedData.isEmpty()
				&&
				iseNumbers.isEmpty()
				&&
				unverifiedProbeData.isEmpty();
	}
}
