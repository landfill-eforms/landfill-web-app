package org.lacitysan.landfill.server.service.surfaceemission.integrated;

import org.lacitysan.landfill.server.persistence.dao.surfaceemission.SurfaceEmissionDataDao;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.integrated.IntegratedDataDao;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.integrated.IntegratedData;
import org.lacitysan.landfill.server.service.surfaceemission.SurfaceEmissionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles the logical operations for <code>IntegratedData</code> entities.
 * @author Alvin Quach
 */
@Service
public class IntegratedDataService extends SurfaceEmissionDataService<IntegratedData> {

	@Autowired
	IntegratedDataDao integratedDataDao;
	
	@Override
	protected SurfaceEmissionDataDao<IntegratedData> getCrudRepository() {
		return integratedDataDao;
	}

}
