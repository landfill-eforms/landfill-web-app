package org.lacitysan.landfill.server.persistence.entity.surfaceemission.integrated;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.SurfaceEmissionExceedanceData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@Entity
@Table(name="dbo.ISEData")
@AttributeOverride(name="id", column=@Column(name="ISEPK"))
@JsonInclude(Include.NON_NULL)
public class IseData extends SurfaceEmissionExceedanceData {
	
	/**
	 * The minimum methane level required in ppm*100 for an integrated reading to be considered an active ISE.
	 * To get the level in ppm, divide this number by 100.
	 */
	public static final int MININIMUM_READING = 2500;
	
	@JsonIgnoreProperties({"iseData"})
	@Cascade(CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name="ISENumberFK")
	private IseNumber iseNumber;
	
	@JsonIgnoreProperties({"iseData"})
	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy="iseData")
	private Set<IseRepairData> iseRepairData = new TreeSet<>();

	public IseNumber getIseNumber() {
		return iseNumber;
	}

	public void setIseNumber(IseNumber iseNumber) {
		this.iseNumber = iseNumber;
	}

	public Set<IseRepairData> getIseRepairData() {
		return iseRepairData;
	}

	public void setIseRepairData(Set<IseRepairData> iseRepairData) {
		this.iseRepairData = iseRepairData;
	}
	
}
