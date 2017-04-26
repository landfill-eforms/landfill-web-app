package org.lacitysan.landfill.server.service.serviceemission.integrated;

import org.lacitysan.landfill.server.persistence.dao.serviceemission.ServiceEmissionDataDao;
import org.lacitysan.landfill.server.persistence.dao.serviceemission.integrated.IntegratedDataDao;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.integrated.IntegratedData;
import org.lacitysan.landfill.server.service.serviceemission.ServiceEmissionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles the logical operations for <code>IntegratedData</code> objects.
 * @author Alvin Quach
 */
@Service
public class IntegratedDataService extends ServiceEmissionDataService<IntegratedData> {

	@Autowired
	IntegratedDataDao integratedDataDao;
	
	@Override
	protected ServiceEmissionDataDao<IntegratedData> getCrudRepository() {
		return integratedDataDao;
	}

}
