package org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.server.persistence.entity.user.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.IMERepairData")
@JsonInclude(Include.NON_NULL)
public class ImeRepairData {
	
	@Id
	@Column(name="IMERepairPK")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnoreProperties({"userGroups"})
	@ManyToOne
	@JoinColumn(name="UserFK")
	private User user;
	
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
	
	@JsonIgnoreProperties(value={"imeRepairData"}, allowSetters=true)
	@NotNull
	@ManyToOne
	@JoinColumn(name="IMEFK")
	private ImeData imeData;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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

	public ImeData getImeData() {
		return imeData;
	}

	public void setImeData(ImeData imeData) {
		this.imeData = imeData;
	}
	
}
