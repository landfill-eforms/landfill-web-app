package org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.ServiceEmissionExceedanceData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.IMEData")
@AttributeOverride(name="id", column=@Column(name="IMEPK"))
@JsonInclude(Include.NON_NULL)
public class ImeData extends ServiceEmissionExceedanceData {
	
	@JsonIgnoreProperties({"imeData"})
	@Cascade(CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name="IMENumberFK")
	private ImeNumber imeNumber;
	
	@JsonIgnoreProperties(value={"imeData"}, allowSetters=true)
	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy="imeData")
	private Set<ImeRepairData> imeRepairData = new HashSet<>();

	public ImeNumber getImeNumber() {
		return imeNumber;
	}

	public void setImeNumber(ImeNumber imeNumber) {
		this.imeNumber = imeNumber;
	}

	public Set<ImeRepairData> getImeRepairData() {
		return imeRepairData;
	}

	public void setImeRepairData(Set<ImeRepairData> imeRepairData) {
		this.imeRepairData = imeRepairData;
	}

}
