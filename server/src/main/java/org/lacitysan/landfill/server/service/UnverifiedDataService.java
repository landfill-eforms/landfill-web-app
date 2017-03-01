package org.lacitysan.landfill.server.service;

import java.util.HashSet;
import java.util.Set;

import org.lacitysan.landfill.server.persistence.dao.instantaneous.IMENumberDao;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedInstantaneousData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alvin Quach
 * @author Allen Huang
 */
@Service
public class UnverifiedDataService {
	
	@Autowired
	MonitoringPointService monitoringPointService;
	
	@Autowired
	IMENumberDao imeNumberDao;
	
	public Set<InstantaneousData> verifyInstantaneousData(UnverifiedDataSet dataSet) {
		
		// Store the verified data here.
		Set<InstantaneousData> result = new HashSet<>();
		
		// Get the barometric pressure from the unverified data set, and check if its value is valid.
		Short barometricPressure = dataSet.getBarometricPressure();
		if (barometricPressure == null || barometricPressure < 2950 || barometricPressure > 3050) {
			return null;
		}
		
		// Go through all the unverified data points.
		for (UnverifiedInstantaneousData data : dataSet.getUnverifiedInstantaneousData()) {
			
			
			
			// If the data point is supposed to be a hotspot.
			if (data.getMethaneLevel() >= 50000) { 
				if (data.getImeNumbers() == null || data.getImeNumbers().isEmpty()) {
					return null;
				}
				
				// TODO Add code to transfer the set of IME Number over.
				
			}
			
			// If the data point is supposed to be a warmspot.
			else if (data.getMethaneLevel() >= 20000) {
				if (data.getWarmspots() == null || data.getWarmspots().isEmpty()) {
					return null;
				}
				
				// TODO Add code to transfer the set of warmspots over.
				
			}
			
			// If the data point is neither a warmspot nor a hotspot, then they shouldnt have any IME Numbers or warmspots.
			else if (!(data.getImeNumbers() == null || data.getImeNumbers().isEmpty()) && !(data.getWarmspots() == null || data.getWarmspots().isEmpty())) {
				return null;
			}
			
			// The verified version of the data point.
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
