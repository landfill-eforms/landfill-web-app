package org.lacitysan.landfill.server.persistence.entity.surfaceemission;

import java.sql.Timestamp;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.server.persistence.entity.AbstractEntity;

/**
 * @author Alvin Quach
 */
@MappedSuperclass
public abstract class SurfaceEmissionExceedanceRepairData extends AbstractEntity implements Comparable<SurfaceEmissionExceedanceRepairData> {
	
	@NotNull
	private Timestamp dateTime;
	
	@NotNull
	private Boolean water;
	
	@NotNull
	private Boolean soil;
	
	@NotNull
	private String description;
	
	@NotNull
	private String crew;

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public Boolean getWater() {
		return water;
	}

	public void setWater(Boolean water) {
		this.water = water;
	}

	public Boolean getSoil() {
		return soil;
	}

	public void setSoil(Boolean soil) {
		this.soil = soil;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCrew() {
		return crew;
	}

	public void setCrew(String crew) {
		this.crew = crew;
	}
	
	@Override
	public int compareTo(SurfaceEmissionExceedanceRepairData o) {
		return -dateTime.compareTo(o.getDateTime()); // Sort by oldest to newest.
	}
	
}
