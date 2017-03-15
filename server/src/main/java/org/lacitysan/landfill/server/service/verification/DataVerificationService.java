package org.lacitysan.landfill.server.service.verification;

import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.WarmspotData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedInstantaneousData;
import org.lacitysan.landfill.server.persistence.enums.Site;
import org.lacitysan.landfill.server.service.verification.model.VerifiedDataSet;
import org.springframework.stereotype.Service;

/**
 * @author Alvin Quach
 */
@Service
public class DataVerificationService {
	
	public VerifiedDataSet verifyInstantaneousData(UnverifiedDataSet unverifiedDataSet) {
		
		// Create a new set to store the verified data.
		VerifiedDataSet result = new VerifiedDataSet();
		
		// TODO Throw an exception instead of returning null.
		
		// This is the site that we are working with for this set of unverified data.
		Site site = unverifiedDataSet.getSite();
		
		// Go through all the unverified instantaneous data points.
		for (UnverifiedInstantaneousData unverifiedInstantaneousData : unverifiedDataSet.getUnverifiedInstantaneousData()) {
			
			// Check if the grid of each data point belongs to the correct site.
			if (unverifiedInstantaneousData.getMonitoringPoint().getSite() != site) {
				return null;
			}
			
			if (unverifiedInstantaneousData.getInstrument() == null) {
				return null;
			}
			
			// Get the barometric pressure from the unverified data point, and check if its value is valid.
			Short barometricPressure = unverifiedInstantaneousData.getBarometricPressure();
			if (barometricPressure == null || barometricPressure < 2950 || barometricPressure > 3050) {
				return null;
			}
			
			// If the data point is supposed to be a hotspot.
			if (unverifiedInstantaneousData.getMethaneLevel() >= 50000) { 
				
				// If the data point is an IME, but doesn't contain any IME numbers...
				if (unverifiedInstantaneousData.getImeNumbers() == null || unverifiedInstantaneousData.getImeNumbers().isEmpty()) {
					return null;
				}
				
				// Validate each of the IME Numbers that are associated with the data point.
				for (ImeNumber imeNumber : unverifiedInstantaneousData.getImeNumbers()) {
					if (imeNumber.getSite() != site) {
						return null;
					}
				}
				
				// TODO Add code to transfer the set of IME Number over.
				
			}
			
			// If the data point is supposed to be a warmspot.
			else if (unverifiedInstantaneousData.getMethaneLevel() >= 20000) {
				
				// If the data point is a warmspot, but doesn't contain any warmspot data...
				if (unverifiedInstantaneousData.getWarmspotData() == null || unverifiedInstantaneousData.getWarmspotData().isEmpty()) {
					return null;
				}
				
				// Validate each of the warmspots that are associated with the data point.
				for (WarmspotData warmspot : unverifiedInstantaneousData.getWarmspotData()) {
					if (warmspot.getMonitoringPoint().getSite() != site) {
						return null;
					}
					if (warmspot.getInstrument() == null) {
						return null;
					}
					
				}
				
				// TODO Add code to transfer the set of warmspots over.
				
			}
			
			// If the data point is neither a warmspot nor a hotspot, then they shouldnt have any IME Numbers or warmspots.
			else if (!(unverifiedInstantaneousData.getImeNumbers() == null || unverifiedInstantaneousData.getImeNumbers().isEmpty()) && !(unverifiedInstantaneousData.getWarmspotData() == null || unverifiedInstantaneousData.getWarmspotData().isEmpty())) {
				return null;
			}
			
			// Create new instantaneous data object and populate its fields with the data from the unverified data point.
			InstantaneousData instantaneousData = new InstantaneousData();
			instantaneousData.setId(0); // The IDs of the new instantaneous data points need to be 0 for the auto increment to work.
			instantaneousData.setInspector(unverifiedDataSet.getInspector());
			instantaneousData.setBarometricPressure(barometricPressure);
			instantaneousData.setStartTime(unverifiedInstantaneousData.getStartTime());
			instantaneousData.setEndTime(unverifiedInstantaneousData.getEndTime());
			instantaneousData.setInstrument(unverifiedInstantaneousData.getInstrument());
			instantaneousData.setMonitoringPoint(unverifiedInstantaneousData.getMonitoringPoint());
			instantaneousData.setMethaneLevel(unverifiedInstantaneousData.getMethaneLevel());
			result.getInstantaneousData().add(instantaneousData);
			
		}
		
		return result;
	}

}
