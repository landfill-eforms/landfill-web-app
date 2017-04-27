package org.lacitysan.landfill.server.persistence.entity.surfaceemission.integrated;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.server.persistence.entity.surfaceemission.SurfaceEmissionData;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.IntegratedData")
@AttributeOverride(name="id", column=@Column(name="IntegratedPK"))
@JsonInclude(Include.NON_NULL)
public class IntegratedData extends SurfaceEmissionData {
	
	@NotNull
	private Short bagNumber;
	
	@NotNull
	private Short volume;
	
	@NotNull
	private String sampleId;

	public Short getBagNumber() {
		return bagNumber;
	}

	public void setBagNumber(Short bagNumber) {
		this.bagNumber = bagNumber;
	}

	public Short getVolume() {
		return volume;
	}

	public void setVolume(Short volume) {
		this.volume = volume;
	}

	public String getSampleId() {
		return sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}
	
}
