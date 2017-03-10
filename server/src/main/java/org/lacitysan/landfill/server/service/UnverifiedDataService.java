package org.lacitysan.landfill.server.service;

import java.util.HashSet;
import java.util.Set;

import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.WarmspotData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedInstantaneousData;
import org.lacitysan.landfill.server.persistence.enums.Site;
import org.springframework.stereotype.Service;

/**
 * @author Alvin Quach
 */
@Service
public class UnverifiedDataService {
	
	public Set<InstantaneousData> verifyInstantaneousData(UnverifiedDataSet dataSet) {
		
		// TODO Throw an exception instead of returning null.
		
		// This is the site that we are working with for this set of unverified data.
		Site site = dataSet.getSite();
		
		// Get the barometric pressure from the unverified data set, and check if its value is valid.
		Short barometricPressure = dataSet.getBarometricPressure();
		if (barometricPressure == null || barometricPressure < 2950 || barometricPressure > 3050) {
			return null;
		}
		
		// Create a new set to store the verified data.
		Set<InstantaneousData> result = new HashSet<>();
		
		// Go through all the unverified data points.
		for (UnverifiedInstantaneousData data : dataSet.getUnverifiedInstantaneousData()) {
			
			// Check if the grid of each data point belongs to the correct site.
			if (data.getMonitoringPoint().getSite() != site) {
				return null;
			}
			
			if (data.getInstrument() == null) {
				return null;
			}
			
			// If the data point is supposed to be a hotspot.
			if (data.getMethaneLevel() >= 50000) { 
				
				// If the data point is an IME, but doesn't contain any IME numbers...
				if (data.getImeNumbers() == null || data.getImeNumbers().isEmpty()) {
					return null;
				}
				
				// Validate each of the IME Numbers that are associated with the data point.
				for (ImeNumber imeNumber : data.getImeNumbers()) {
					if (imeNumber.getSite() != site) {
						return null;
					}
				}
				
				// TODO Add code to transfer the set of IME Number over.
				
			}
			
			// If the data point is supposed to be a warmspot.
			else if (data.getMethaneLevel() >= 20000) {
				
				// If the data piont is a warmspot, but doesn't contain any warmspot data...
				if (data.getWarmspotData() == null || data.getWarmspotData().isEmpty()) {
					return null;
				}
				
				// Validate each of the warmspots that are associated with the data point.
				for (WarmspotData warmspot : data.getWarmspotData()) {
					if (warmspot.getMonitoringPoint().getSite() != site) {
						return null;
					}
				}
				
				// TODO Add code to transfer the set of warmspots over.
				
			}
			
			// If the data point is neither a warmspot nor a hotspot, then they shouldnt have any IME Numbers or warmspots.
			else if (!(data.getImeNumbers() == null || data.getImeNumbers().isEmpty()) && !(data.getWarmspotData() == null || data.getWarmspotData().isEmpty())) {
				return null;
			}
			
			// Create new instantaneous data object and populate its fields with the data from the unverified data point.
			InstantaneousData instantaneousData = new InstantaneousData();
			instantaneousData.setId(0); // The IDs of the new instantaneous data points need to be 0 for the auto increment to work.
			instantaneousData.setInspector(dataSet.getInspector());
			instantaneousData.setBarometricPressure(barometricPressure);
			instantaneousData.setStartTime(data.getStartTime());
			instantaneousData.setEndTime(data.getEndTime());
			instantaneousData.setInstrument(data.getInstrument());
			instantaneousData.setMonitoringPoint(data.getMonitoringPoint());
			instantaneousData.setMethaneLevel(data.getMethaneLevel());
			result.add(instantaneousData);
			
		}
		
		return result;
	}

}
