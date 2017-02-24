package org.lacitysan.landfill.server.persistence.entity.instantaneous;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.lacitysan.landfill.server.persistence.entity.user.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name=ApplicationProperty.DATABASE_NAME + ".dbo.IMEData")
public class IMEData {
	
	@Id
	@Column(name="IMEPK")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnoreProperties({"imeData"})
	@Cascade(CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name="IMENumberFK")
	private IMENumber imeNumber;
	
	@JsonIgnoreProperties({"password", "userGroups", "enabled", "person"})
	@ManyToOne
	@JoinColumn(name="InspectorFK")
	private User inspector;
	
	@NotNull
	private Integer methaneLevel;
	
	@NotNull
	private Timestamp dateTime;
	
	@NotNull
	private String description;
	
	@OneToOne
	@Cascade(CascadeType.ALL)
	@JoinColumn(name="IMERepairFK")
	private IMERepairData imeRepairData;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public IMENumber getImeNumber() {
		return imeNumber;
	}

	public void setImeNumber(IMENumber imeNumber) {
		this.imeNumber = imeNumber;
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

	public IMERepairData getImeRepairData() {
		return imeRepairData;
	}

	public void setImeRepairData(IMERepairData imeRepairData) {
		this.imeRepairData = imeRepairData;
	}

}
