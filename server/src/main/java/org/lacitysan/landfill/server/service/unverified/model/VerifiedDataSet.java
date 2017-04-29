package org.lacitysan.landfill.server.service.unverified.model;

import java.util.HashSet;
import java.util.Set;

import org.lacitysan.landfill.server.persistence.entity.probe.ProbeData;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.WarmspotData;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.integrated.IntegratedData;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.integrated.IseNumber;

/**
 * @author Alvin Quach
 */
public class VerifiedDataSet {

	Set<InstantaneousData> instantaneousData = new HashSet<>();
	
	Set<WarmspotData> warmspotData = new HashSet<>();
	
	Set<ImeNumber> imeNumbers = new HashSet<>();
	
	Set<IntegratedData> integratedData = new HashSet<>();
	
	Set<IseNumber> iseNumbers = new HashSet<>();
	
	Set<ProbeData> probeData = new HashSet<>();

	public Set<InstantaneousData> getInstantaneousData() {
		return instantaneousData;
	}

	public void setInstantaneousData(Set<InstantaneousData> instantaneousData) {
		this.instantaneousData = instantaneousData;
	}

	public Set<WarmspotData> getWarmspotData() {
		return warmspotData;
	}

	public void setWarmspotData(Set<WarmspotData> warmspotData) {
		this.warmspotData = warmspotData;
	}

	public Set<ImeNumber> getImeNumbers() {
		return imeNumbers;
	}

	public void setImeNumbers(Set<ImeNumber> imeNumbers) {
		this.imeNumbers = imeNumbers;
	}

	public Set<IntegratedData> getIntegratedData() {
		return integratedData;
	}

	public void setIntegratedData(Set<IntegratedData> integratedData) {
		this.integratedData = integratedData;
	}

	public Set<IseNumber> getIseNumbers() {
		return iseNumbers;
	}

	public void setIseNumbers(Set<IseNumber> iseNumbers) {
		this.iseNumbers = iseNumbers;
	}

	public Set<ProbeData> getProbeData() {
		return probeData;
	}

	public void setProbeData(Set<ProbeData> probeData) {
		this.probeData = probeData;
	}
	
}
