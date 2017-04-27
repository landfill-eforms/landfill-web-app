package org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.server.persistence.entity.surfaceemission.SurfaceEmissionExceedanceRepairData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.IMERepairData")
@AttributeOverride(name="id", column=@Column(name="IMERepairPK"))
@JsonInclude(Include.NON_NULL)
public class ImeRepairData extends SurfaceEmissionExceedanceRepairData {
	
	@JsonIgnoreProperties(value={"imeRepairData"}, allowSetters=true)
	@NotNull
	@ManyToOne
	@JoinColumn(name="IMEFK")
	private ImeData imeData;

	public ImeData getImeData() {
		return imeData;
	}

	public void setImeData(ImeData imeData) {
		this.imeData = imeData;
	}
	
}
