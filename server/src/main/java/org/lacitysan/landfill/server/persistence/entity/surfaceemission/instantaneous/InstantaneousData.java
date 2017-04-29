package org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.lacitysan.landfill.server.persistence.entity.surfaceemission.SurfaceEmissionData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.InstantaneousData")
@AttributeOverride(name="id", column=@Column(name="InstantaneousPK"))
@JsonInclude(Include.NON_NULL)
public class InstantaneousData extends SurfaceEmissionData {
	
	@JsonIgnoreProperties({"unverifiedInstantaneousData", "monitoringPoints", "instantaneousData", "imeData", "imeRepairData"})
	@ManyToMany
	@JoinTable(name="dbo.InstantaneousDataXRefIMENumbers", joinColumns=@JoinColumn(name="InstantaneousFK"), inverseJoinColumns=@JoinColumn(name="IMENumberFK"))
	private Set<ImeNumber> imeNumbers = new TreeSet<>();

	public Set<ImeNumber> getImeNumbers() {
		return imeNumbers;
	}

	public void setImeNumbers(Set<ImeNumber> imeNumbers) {
		this.imeNumbers = imeNumbers;
	}
	
}