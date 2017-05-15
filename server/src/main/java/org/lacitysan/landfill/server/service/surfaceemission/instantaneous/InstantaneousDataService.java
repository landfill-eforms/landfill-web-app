package org.lacitysan.landfill.server.service.surfaceemission.instantaneous;

import org.lacitysan.landfill.server.persistence.dao.surfaceemission.SurfaceEmissionDataDao;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.instantaneous.InstantaneousDataDao;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.service.surfaceemission.SurfaceEmissionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles the logical operations for <code>InstantaneousData</code> entities.
 * @author Alvin Quach
 */
@Service
public class InstantaneousDataService extends SurfaceEmissionDataService<InstantaneousData> {

	@Autowired
	InstantaneousDataDao instantaneousDataDao;
	
	@Override
	protected SurfaceEmissionDataDao<InstantaneousData> getCrudRepository() {
		return instantaneousDataDao;
	}

}
