package org.lacitysan.landfill.server.persistence.entity.instrument;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.server.persistence.entity.AbstractEntity;
import org.lacitysan.landfill.server.persistence.entity.system.Trackable;
import org.lacitysan.landfill.server.persistence.entity.user.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.InstrumentTypes")
@AttributeOverride(name="id", column=@Column(name="InstrumentTypePK"))
@JsonInclude(Include.NON_NULL)
public class InstrumentType extends AbstractEntity implements Trackable {

	@NotNull
	private String type;

	@NotNull
	private String manufacturer;

	@NotNull
	private String description;

	@NotNull
	private Boolean instantaneous;

	@NotNull
	private Boolean probe;

	@NotNull
	private Boolean methanePercent;

	@NotNull
	private Boolean methanePpm;

	@NotNull
	private Boolean hydrogenSulfidePpm;

	@NotNull
	private Boolean oxygenPercent;

	@NotNull
	private Boolean carbonDioxidePercent;

	@NotNull
	private Boolean nitrogenPercent;

	@NotNull
	private Boolean pressure;

	@JsonIgnoreProperties(value="instrumentType", allowSetters=true)
	@OneToMany(mappedBy="instrumentType")
	private Set<Instrument> instruments = new HashSet<>();

	@JsonIgnoreProperties(value={"userGroups", "enabled", "lastLogin", "createdBy", "createdDate", "modifiedBy", "modifiedDate"}, allowSetters=true)
	@ManyToOne
	@JoinColumn(name="CreatedByFK")
	private User createdBy;

	private Timestamp createdDate;

	@JsonIgnoreProperties(value={"userGroups", "enabled", "lastLogin", "createdBy", "createdDate", "modifiedBy", "modifiedDate"}, allowSetters=true)
	@ManyToOne
	@JoinColumn(name="ModifiedByFK")
	private User modifiedBy;

	private Timestamp modifiedDate;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getInstantaneous() {
		return instantaneous;
	}

	public void setInstantaneous(Boolean instantaneous) {
		this.instantaneous = instantaneous;
	}

	public Boolean getProbe() {
		return probe;
	}

	public void setProbe(Boolean probe) {
		this.probe = probe;
	}

	public Boolean getMethanePercent() {
		return methanePercent;
	}

	public void setMethanePercent(Boolean methanePercent) {
		this.methanePercent = methanePercent;
	}

	public Boolean getMethanePpm() {
		return methanePpm;
	}

	public void setMethanePpm(Boolean methanePpm) {
		this.methanePpm = methanePpm;
	}

	public Boolean getHydrogenSulfidePpm() {
		return hydrogenSulfidePpm;
	}

	public void setHydrogenSulfidePpm(Boolean hydrogenSulfidePpm) {
		this.hydrogenSulfidePpm = hydrogenSulfidePpm;
	}

	public Boolean getOxygenPercent() {
		return oxygenPercent;
	}

	public void setOxygenPercent(Boolean oxygenPercent) {
		this.oxygenPercent = oxygenPercent;
	}

	public Boolean getCarbonDioxidePercent() {
		return carbonDioxidePercent;
	}

	public void setCarbonDioxidePercent(Boolean carbonDioxidePercent) {
		this.carbonDioxidePercent = carbonDioxidePercent;
	}

	public Boolean getNitrogenPercent() {
		return nitrogenPercent;
	}

	public void setNitrogenPercent(Boolean nitrogenPercent) {
		this.nitrogenPercent = nitrogenPercent;
	}

	public Boolean getPressure() {
		return pressure;
	}

	public void setPressure(Boolean pressure) {
		this.pressure = pressure;
	}

	public Set<Instrument> getInstruments() {
		return instruments;
	}

	public void setInstruments(Set<Instrument> instruments) {
		this.instruments = instruments;
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

}
