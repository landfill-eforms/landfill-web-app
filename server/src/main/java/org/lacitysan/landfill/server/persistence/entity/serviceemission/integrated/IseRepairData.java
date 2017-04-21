package org.lacitysan.landfill.server.persistence.entity.serviceemission.integrated;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.server.persistence.entity.serviceemission.ServiceEmissionExceedanceRepairData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.ISERepairData")
@AttributeOverride(name="id", column=@Column(name="ISERepairPK"))
@JsonInclude(Include.NON_NULL)
public class IseRepairData extends ServiceEmissionExceedanceRepairData {
	
	@JsonIgnoreProperties({"iseRepairData"})
	@NotNull
	@ManyToOne
	@JoinColumn(name="ISEFK")
	private IseData iseData;

	public IseData getIseData() {
		return iseData;
	}

	public void setIseData(IseData iseData) {
		this.iseData = iseData;
	}

}
