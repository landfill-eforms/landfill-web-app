package org.lacitysan.landfill.server.persistence.entity.integrated;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AssociationOverride;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.lacitysan.landfill.server.persistence.entity.exceedance.ServiceEmissionsExceedanceNumber;
import org.lacitysan.landfill.server.service.integrated.IseService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.ISENumbers")
@AttributeOverride(name="id", column=@Column(name="ISENumberPK"))
@AssociationOverride(name="monitoringPoints", joinTable=@JoinTable(name="dbo.ISENumbersXRefMonitoringPoints", joinColumns=@JoinColumn(name="ISENumberFK")))
@JsonInclude(Include.NON_NULL)
public class IseNumber extends ServiceEmissionsExceedanceNumber {
	
	@JsonIgnoreProperties("iseNumber")
	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy="iseNumber")
	private Set<IseData> iseData = new HashSet<>();

	@Transient
	private String iseNumber;

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

}
