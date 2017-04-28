package org.lacitysan.landfill.server.service.unverified.model;

import java.util.HashSet;
import java.util.Set;

import org.lacitysan.landfill.server.persistence.entity.probe.ProbeData;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.WarmspotData;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.integrated.IntegratedData;

/**
 * @author Alvin Quach
 */
public class VerifiedDataSet {

	Set<InstantaneousData> instantaneousData = new HashSet<>();
	
	Set<IntegratedData> integratedData = new HashSet<>();
	
	Set<ProbeData> probeData = new HashSet<>();
	
	// TODO Remove this?
	Set<WarmspotData> warmspotData = new HashSet<>();

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

	public Set<IntegratedData> getIntegratedData() {
		return integratedData;
	}

	public void setIntegratedData(Set<IntegratedData> integratedData) {
		this.integratedData = integratedData;
	}

	public Set<ProbeData> getProbeData() {
		return probeData;
	}

	public void setProbeData(Set<ProbeData> probeData) {
		this.probeData = probeData;
	}
	
}
