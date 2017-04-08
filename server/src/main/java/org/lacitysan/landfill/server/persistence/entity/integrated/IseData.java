package org.lacitysan.landfill.server.persistence.entity.integrated;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.lacitysan.landfill.server.persistence.entity.user.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.ISEData")
@JsonInclude(Include.NON_NULL)
public class IseData {

	@Id
	@Column(name="ISEPK")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnoreProperties({"iseData"})
	@Cascade(CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name="ISENumberFK")
	private IseNumber iseNumber;
	
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
	
	@JsonIgnoreProperties({"iseData"})
	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy="iseData")
	private Set<IseRepairData> iseRepairData = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public IseNumber getIseNumber() {
		return iseNumber;
	}

	public void setIseNumber(IseNumber iseNumber) {
		this.iseNumber = iseNumber;
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

	public Set<IseRepairData> getIseRepairData() {
		return iseRepairData;
	}

	public void setIseRepairData(Set<IseRepairData> iseRepairData) {
		this.iseRepairData = iseRepairData;
	}
	
}
