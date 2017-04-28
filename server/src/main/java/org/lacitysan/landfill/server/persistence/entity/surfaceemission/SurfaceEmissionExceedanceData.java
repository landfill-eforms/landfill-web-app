package org.lacitysan.landfill.server.persistence.entity.surfaceemission;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.server.persistence.entity.user.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Alvin Quach
 */
@MappedSuperclass
public abstract class SurfaceEmissionExceedanceData implements Comparable<SurfaceEmissionExceedanceData> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnoreProperties({"userGroups", "enabled"})
	@ManyToOne
	@JoinColumn(name="InspectorFK")
	private User inspector;
	
	@NotNull
	private Integer methaneLevel;
	
	@NotNull
	private Timestamp dateTime;
	
	@NotNull
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getInspector() {
		return inspector;
	}

	public void setInspector(User inspector) {
		this.inspector = inspector;
	}

	public Integer getMethaneLevel() {
		return methaneLevel;
	}

	public void setMethaneLevel(Integer methaneLevel) {
		this.methaneLevel = methaneLevel;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public int compareTo(SurfaceEmissionExceedanceData o) {
		return dateTime.compareTo(o.getDateTime());
	}

}
