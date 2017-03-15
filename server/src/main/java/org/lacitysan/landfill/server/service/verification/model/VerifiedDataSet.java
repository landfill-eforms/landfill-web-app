package org.lacitysan.landfill.server.service.verification.model;

import java.util.HashSet;
import java.util.Set;

import org.lacitysan.landfill.server.persistence.entity.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.WarmspotData;

/**
 * @author Alvin Quach
 */
public class VerifiedDataSet {

	Set<InstantaneousData> instantaneousData = new HashSet<>();
	
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
	
}
