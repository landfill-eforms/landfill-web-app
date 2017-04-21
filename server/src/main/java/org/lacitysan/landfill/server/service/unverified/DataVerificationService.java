package org.lacitysan.landfill.server.service.unverified;

import java.sql.Date;

import org.lacitysan.landfill.server.persistence.dao.probe.ProbeDataDao;
import org.lacitysan.landfill.server.persistence.dao.serviceemission.instantaneous.InstantaneousDataDao;
import org.lacitysan.landfill.server.persistence.dao.serviceemission.instantaneous.WarmspotDataDao;
import org.lacitysan.landfill.server.persistence.dao.serviceemission.integrated.IntegratedDataDao;
import org.lacitysan.landfill.server.persistence.dao.unverified.UnverifiedDataSetDao;
import org.lacitysan.landfill.server.persistence.entity.probe.ProbeData;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous.WarmspotData;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.integrated.IntegratedData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedInstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedIntegratedData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedProbeData;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.lacitysan.landfill.server.persistence.enums.exceedance.ExceedanceStatus;
import org.lacitysan.landfill.server.persistence.enums.location.Site;
import org.lacitysan.landfill.server.service.serviceemission.instantaneous.ImeService;
import org.lacitysan.landfill.server.service.unverified.model.VerifiedDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alvin Quach
 */
@Service
public class DataVerificationService {
	
	@Autowired
	UnverifiedDataSetDao unverifiedDataSetDao;
	
	@Autowired
	InstantaneousDataDao instantaneousDataDao;
	
	@Autowired
	IntegratedDataDao integrateDataDao;
	
	@Autowired
	ProbeDataDao probeDataDao;
	
	@Autowired
	WarmspotDataDao warmspotDataDao;
	
	@Autowired
	ImeService imeService;
	
	public VerifiedDataSet verifyAndCommit(UnverifiedDataSet unverifiedDataSet) {
		
		// Create a new set to store the verified data.
		VerifiedDataSet result = new VerifiedDataSet();
		
		// This is the site that we are working with for this set of unverified data.
		Site site = unverifiedDataSet.getSite();
		
		// This is the inspector that we are working with for this set of unverified data.
		User inspector = unverifiedDataSet.getInspector();
		
		// Go through all the unverified instantaneous data points.
		for (UnverifiedInstantaneousData unverifiedInstantaneousData : unverifiedDataSet.getUnverifiedInstantaneousData()) {
			InstantaneousData instantaneousData = verifyInstantaneousData(unverifiedInstantaneousData, site, inspector);
			if (instantaneousData == null) {
				// TODO Throw an exception instead of returning null.
				return null;
			}
			result.getInstantaneousData().add(instantaneousData);
		}
		
		// Go through all the unverified integrated data points.
		for (UnverifiedIntegratedData unverifiedIntegratedData : unverifiedDataSet.getUnverifiedIntegratedData()) {
			IntegratedData integratedData = verifyIntegratedData(unverifiedIntegratedData, site, inspector);
			if (integratedData == null) {
				// TODO Throw an exception instead of returning null.
				return null;
			}
			result.getIntegratedData().add(integratedData);
		}
		
		// Go through all the unverified probe data points.
		for (UnverifiedProbeData unverifiedProbeData : unverifiedDataSet.getUnverifiedProbeData()) {
			ProbeData probeData = verifyProbeData(unverifiedProbeData);
			if (probeData == null) {
				// TODO Throw an exception instead of returning null.
				return null;
			}
			result.getProbeData().add(probeData);
		}
		
		// *** All data should be verified and good to go by this point.
		
		unverifiedDataSetDao.delete(unverifiedDataSet);
		
		for (InstantaneousData instantaneousData : result.getInstantaneousData()) {
			
			// Update IME Number verified status.
			for (ImeNumber imeNumber : instantaneousData.getImeNumbers()) {
				if (imeNumber.getStatus() == ExceedanceStatus.UNVERIFIED) {
					imeService.verify(imeNumber);
				}
			}
			
			// Insert verified instantaneous data into database.
			instantaneousDataDao.create(instantaneousData);

		}
		
		for (IntegratedData integratedData : result.getIntegratedData()) {
			integrateDataDao.create(integratedData);
		}
		
		for (ProbeData probeData : result.getProbeData()) {
			probeDataDao.create(probeData);
		}
		
		return result;
		
	}
	
	public InstantaneousData verifyInstantaneousData(UnverifiedInstantaneousData unverifiedInstantaneousData, Site site, User inspector) {
		
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
		
		// Create new instantaneous data object and populate its fields with the data from the unverified data point.
		InstantaneousData instantaneousData = new InstantaneousData();
		
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
				instantaneousData.getImeNumbers().add(imeNumber);
			}
			
			// TODO Add code to transfer the set of IME Number over.
			
		}
		
		// If the data point is supposed to be a warmspot.
		else if (unverifiedInstantaneousData.getMethaneLevel() >= 20000) {
			
			// If the data point is a warmspot, but doesn't contain any warmspot data...
			if (unverifiedInstantaneousData.getUnverifiedWarmspotData() == null) {
				return null; 
			}
			
			WarmspotData warmspotData = new WarmspotData();
			warmspotData.setMonitoringPoint(unverifiedInstantaneousData.getMonitoringPoint());
			warmspotData.setInstrument(unverifiedInstantaneousData.getInstrument());
			warmspotData.setInspector(inspector);
			warmspotData.setMethaneLevel(unverifiedInstantaneousData.getMethaneLevel());
			warmspotData.setDate(new Date(unverifiedInstantaneousData.getStartTime().getTime()));
			warmspotData.setDescription(unverifiedInstantaneousData.getUnverifiedWarmspotData().getDescription());
			warmspotData.setSize(unverifiedInstantaneousData.getUnverifiedWarmspotData().getSize());
			instantaneousData.setWarmspotData(warmspotData);
			
		}
		
		// If the data point is neither a warmspot nor a hotspot, then they shouldn't have any IME Numbers or warmspots.
		else if ((unverifiedInstantaneousData.getImeNumbers() != null && !unverifiedInstantaneousData.getImeNumbers().isEmpty()) || unverifiedInstantaneousData.getUnverifiedWarmspotData() != null) {
			return null;
		}
		
		instantaneousData.setInspector(inspector);
		instantaneousData.setBarometricPressure(barometricPressure);
		instantaneousData.setStartTime(unverifiedInstantaneousData.getStartTime());
		instantaneousData.setEndTime(unverifiedInstantaneousData.getEndTime());
		instantaneousData.setInstrument(unverifiedInstantaneousData.getInstrument());
		instantaneousData.setMonitoringPoint(unverifiedInstantaneousData.getMonitoringPoint());
		instantaneousData.setMethaneLevel(unverifiedInstantaneousData.getMethaneLevel());
			
		return instantaneousData;
	}
	
	public IntegratedData verifyIntegratedData(UnverifiedIntegratedData unverifiedIntegratedData, Site site, User inspector) {
		
		// Check if the grid of each data point belongs to the correct site.
		if (unverifiedIntegratedData.getMonitoringPoint().getSite() != site) {
			return null;
		}
		
		if (unverifiedIntegratedData.getInstrument() == null) {
			return null;
		}
		
		// Get the barometric pressure from the unverified data point, and check if its value is valid.
		Short barometricPressure = unverifiedIntegratedData.getBarometricPressure();
		if (barometricPressure == null || barometricPressure < 2950 || barometricPressure > 3050) {
			return null;
		}
		
		// Create new integrated data object and populate its fields with the data from the unverified data point.
		IntegratedData integratedData = new IntegratedData();
		integratedData.setInspector(inspector);
		integratedData.setBarometricPressure(barometricPressure);
		integratedData.setStartTime(unverifiedIntegratedData.getStartTime());
		integratedData.setEndTime(unverifiedIntegratedData.getEndTime());
		integratedData.setInstrument(unverifiedIntegratedData.getInstrument());
		integratedData.setMonitoringPoint(unverifiedIntegratedData.getMonitoringPoint());
		integratedData.setMethaneLevel(unverifiedIntegratedData.getMethaneLevel());
		integratedData.setBagNumber(unverifiedIntegratedData.getBagNumber());
		integratedData.setVolume(unverifiedIntegratedData.getVolume());
		
		return integratedData;
	}
	
	public ProbeData verifyProbeData(UnverifiedProbeData unverifiedProbeData) {
		Short barometricPressure = unverifiedProbeData.getBarometricPressure();
		if (barometricPressure == null || barometricPressure < 2950 || barometricPressure > 3050) {
			return null;
		}
		ProbeData probeData = new ProbeData();
		probeData.setAccessible(unverifiedProbeData.getAccessible());
		probeData.setBarometricPressure(unverifiedProbeData.getBarometricPressure());
		probeData.setDate(unverifiedProbeData.getDate());
		probeData.setDescription(unverifiedProbeData.getDescription());
		probeData.setInspectors(unverifiedProbeData.getInspectors());
		probeData.setMethaneLevel(unverifiedProbeData.getMethaneLevel());
		probeData.setMonitoringPoint(unverifiedProbeData.getMonitoringPoint());
		probeData.setPressureLevel(unverifiedProbeData.getPressureLevel());
		return probeData;
	}

}
