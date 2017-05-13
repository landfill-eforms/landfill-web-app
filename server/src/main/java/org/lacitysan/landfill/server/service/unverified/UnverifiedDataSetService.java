package org.lacitysan.landfill.server.service.unverified;

import org.lacitysan.landfill.server.persistence.dao.surfaceemission.instantaneous.ImeNumberDao;
import org.lacitysan.landfill.server.persistence.dao.unverified.UnverifiedDataSetDao;
import org.lacitysan.landfill.server.persistence.dao.unverified.UnverifiedInstantaneousDataDao;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.ImeData;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.integrated.IseData;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.integrated.IseNumber;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedInstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedIntegratedData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedProbeData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedWarmspotData;
import org.lacitysan.landfill.server.service.system.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alvin Quach
 */
@Service
public class UnverifiedDataSetService {
	
	@Autowired
	UnverifiedDataSetDao unverifiedDataSetDao;
	
	@Autowired
	ImeNumberDao imeNumberDao;
	
	@Autowired
	UnverifiedInstantaneousDataDao unverifiedInstantaneousDataDao;
	
	@Autowired
	TrackingService trackingService;

	public UnverifiedDataSet create(UnverifiedDataSet unverifiedDataSet) {
		checkRelations(unverifiedDataSet);
		trackingService.create(unverifiedDataSet);
		return unverifiedDataSetDao.create(unverifiedDataSet);
	}
	
	public UnverifiedDataSet update(UnverifiedDataSet unverifiedDataSet) {
		checkRelations(unverifiedDataSet);
		trackingService.modify(unverifiedDataSet);
		return unverifiedDataSetDao.update(unverifiedDataSet);
	}
	
	public UnverifiedDataSet delete(UnverifiedDataSet unverifiedDataSet) {
		
		// Load the data set from the database.
		unverifiedDataSet = unverifiedDataSetDao.getById(unverifiedDataSet.getId());
		
		// Remove relations between other data sets and this data set's IME numbers.
		for (ImeNumber imeNumber : unverifiedDataSet.getImeNumbers()) {
			imeNumber = imeNumberDao.getById(imeNumber.getId());
			for (UnverifiedInstantaneousData unverifiedInstantaneousData : imeNumber.getUnverifiedInstantaneousData()) {
				
				// Load the unverified instantaneous data from database.
				unverifiedInstantaneousData = unverifiedInstantaneousDataDao.getById(unverifiedInstantaneousData.getId());
				
				// Remove references from the unverified instantaneous data to the IME number.
				Integer id = imeNumber.getId();
				unverifiedInstantaneousData.getImeNumbers().removeIf(i -> i.getId().equals(id));
				unverifiedInstantaneousDataDao.update(unverifiedInstantaneousData);
			}
			
			// Delete the IME number.
			imeNumberDao.delete(imeNumber);
		}
		
		return unverifiedDataSetDao.deleteSafe(unverifiedDataSet);
	}
	
	private UnverifiedDataSet checkRelations(UnverifiedDataSet unverifiedDataSet) {
		for (UnverifiedInstantaneousData unverifiedInstantaneousData : unverifiedDataSet.getUnverifiedInstantaneousData()) {
			if (unverifiedInstantaneousData.getInstrument() == null || unverifiedInstantaneousData.getInstrument().getId() == null) {
				unverifiedInstantaneousData.setInstrument(null);
			}
			unverifiedInstantaneousData.setUnverifiedDataSet(unverifiedDataSet);
		}
		for (UnverifiedWarmspotData unverifiedWarmspotData : unverifiedDataSet.getUnverifiedWarmspotData()) {
			if (unverifiedWarmspotData.getInstrument() == null || unverifiedWarmspotData.getInstrument().getId() == null) {
				unverifiedWarmspotData.setInstrument(null);
			}
			unverifiedWarmspotData.setUnverifiedDataSet(unverifiedDataSet);
		}
		for (ImeNumber imeNumber : unverifiedDataSet.getImeNumbers()) {
			imeNumber.setUnverifiedDataSet(unverifiedDataSet);
			for (ImeData imeData : imeNumber.getImeData()) {
				imeData.setImeNumber(imeNumber);
			}
		}
		for (UnverifiedIntegratedData unverifiedIntegratedData : unverifiedDataSet.getUnverifiedIntegratedData()) {
			if (unverifiedIntegratedData.getInstrument() == null || unverifiedIntegratedData.getInstrument().getId() == null) {
				unverifiedIntegratedData.setInstrument(null);
			}
			unverifiedIntegratedData.setUnverifiedDataSet(unverifiedDataSet);
		}
		for (IseNumber iseNumber : unverifiedDataSet.getIseNumbers()) {
			iseNumber.setUnverifiedDataSet(unverifiedDataSet);
			iseNumber.setUnverifiedDataSet(unverifiedDataSet);
			for (IseData iseData : iseNumber.getIseData()) {
				iseData.setIseNumber(iseNumber);
			}
		}
		for (UnverifiedProbeData unverifiedProbeData : unverifiedDataSet.getUnverifiedProbeData()) {
			unverifiedProbeData.setUnverifiedDataSet(unverifiedDataSet);
		}
		return unverifiedDataSet;
	}
	
}
