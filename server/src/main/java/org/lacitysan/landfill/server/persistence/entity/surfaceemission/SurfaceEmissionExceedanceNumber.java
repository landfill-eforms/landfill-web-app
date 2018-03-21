package org.lacitysan.landfill.server.persistence.entity.surfaceemission;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.lacitysan.landfill.server.persistence.entity.AbstractEntity;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.lacitysan.landfill.server.persistence.enums.exceedance.ExceedanceStatus;
import org.lacitysan.landfill.server.persistence.enums.location.Site;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * An alphanumeric identifier for a surface emissions exceedance, such as an IME or ISE.
 * The format of the identifier is AAyyMM-BB, where AA is the short name of the site, yyMM is the date format, and BB is the sequence number.
 * @author Alvin Quach
 */
@MappedSuperclass
public abstract class SurfaceEmissionExceedanceNumber extends AbstractEntity implements Comparable<SurfaceEmissionExceedanceNumber> {

	@NotNull
	@Column(name="SiteString")
	@Enumerated(EnumType.STRING)
	private Site site;

	@NotNull
	private Short dateCode;

	@NotNull
	private Short sequence;

	@NotNull
	@Column(name="StatusString")
	@Enumerated(EnumType.STRING)
	private ExceedanceStatus status;

	@JsonIgnoreProperties(value={
			"unverifiedInstantaneousData", 
			"unverifiedWarmspotData", 
			"imeNumbers", 
			"unverifiedIntegratedData", 
			"iseNumbers", 
			"unverifiedProbeData", 
			"probeExceedances", 
			"inspector", 
			"createdBy",
			"createdDate",
			"modifiedBy",
			"modifiedDate"
	}, allowSetters=true)
	@ManyToOne
	@JoinColumn(name="UnverifiedDataSetFK")
	private UnverifiedDataSet unverifiedDataSet;

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Short getDateCode() {
		return dateCode;
	}

	public void setDateCode(Short dateCode) {
		this.dateCode = dateCode;
	}

	public Short getSequence() {
		return sequence;
	}

	public void setSequence(Short sequence) {
		this.sequence = sequence;
	}

	public ExceedanceStatus getStatus() {
		return status;
	}

	public void setStatus(ExceedanceStatus status) {
		this.status = status;
	}

	public UnverifiedDataSet getUnverifiedDataSet() {
		return unverifiedDataSet;
	}

	public void setUnverifiedDataSet(UnverifiedDataSet unverifiedDataSet) {
		this.unverifiedDataSet = unverifiedDataSet;
	}

	@Override
	abstract public String toString();

	@Override
	public int compareTo(SurfaceEmissionExceedanceNumber o) {
		if (site != o.getSite()) {
			return this.site.compareTo(o.getSite());
		}
		if (!dateCode.equals(o.getDateCode())) {
			return this.dateCode - o.getDateCode();
		}
		if (!sequence.equals(o.getSequence())) {
			return this.sequence - o.getSequence();
		}
		if (status != o.getStatus()) {
			return status.compareTo(o.getStatus());
		}
		return 0;
	}

}
