package org.lacitysan.landfill.server.service.unverified;

import org.lacitysan.landfill.server.persistence.dao.unverified.UnverifiedDataSetDao;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedInstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedIntegratedData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedProbeData;
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
	TrackingService trackingService;

	public UnverifiedDataSet create(UnverifiedDataSet unverifiedDataSet) {
		for (UnverifiedInstantaneousData unverifiedInstantaneousData : unverifiedDataSet.getUnverifiedInstantaneousData()) {
			if (unverifiedInstantaneousData.getInstrument() == null || unverifiedInstantaneousData.getInstrument().getId() == null) {
				unverifiedInstantaneousData.setInstrument(null);
			}
			unverifiedInstantaneousData.setUnverifiedDataSet(unverifiedDataSet);
		}
		for (UnverifiedIntegratedData unverifiedIntegratedData : unverifiedDataSet.getUnverifiedIntegratedData()) {
			if (unverifiedIntegratedData.getInstrument() == null || unverifiedIntegratedData.getInstrument().getId() == null) {
				unverifiedIntegratedData.setInstrument(null);
			}
			unverifiedIntegratedData.setUnverifiedDataSet(unverifiedDataSet);
		}
		for (UnverifiedProbeData unverifiedProbeData : unverifiedDataSet.getUnverifiedProbeData()) {
			unverifiedProbeData.setUnverifiedDataSet(unverifiedDataSet);
		}
		trackingService.create(unverifiedDataSet);
		return unverifiedDataSetDao.create(unverifiedDataSet);
	}
	
	public UnverifiedDataSet update(UnverifiedDataSet unverifiedDataSet) {
		for (UnverifiedInstantaneousData unverifiedInstantaneousData : unverifiedDataSet.getUnverifiedInstantaneousData()) {
			if (unverifiedInstantaneousData.getInstrument() == null || unverifiedInstantaneousData.getInstrument().getId() == null) {
				unverifiedInstantaneousData.setInstrument(null);
			}
			unverifiedInstantaneousData.setUnverifiedDataSet(unverifiedDataSet);
		}
		for (UnverifiedIntegratedData unverifiedIntegratedData : unverifiedDataSet.getUnverifiedIntegratedData()) {
			if (unverifiedIntegratedData.getInstrument() == null || unverifiedIntegratedData.getInstrument().getId() == null) {
				unverifiedIntegratedData.setInstrument(null);
			}
			unverifiedIntegratedData.setUnverifiedDataSet(unverifiedDataSet);
		}
		for (UnverifiedProbeData unverifiedProbeData : unverifiedDataSet.getUnverifiedProbeData()) {
			unverifiedProbeData.setUnverifiedDataSet(unverifiedDataSet);
		}
		trackingService.modify(unverifiedDataSet);
		UnverifiedDataSet asdf =  unverifiedDataSetDao.update(unverifiedDataSet);
		asdf = unverifiedDataSetDao.getById(asdf.getId());
		return asdf;
	}
	
}
