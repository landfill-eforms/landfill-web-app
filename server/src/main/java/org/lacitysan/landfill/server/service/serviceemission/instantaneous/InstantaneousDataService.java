package org.lacitysan.landfill.server.service.serviceemission.instantaneous;

import org.lacitysan.landfill.server.persistence.dao.serviceemission.ServiceEmissionDataDao;
import org.lacitysan.landfill.server.persistence.dao.serviceemission.instantaneous.InstantaneousDataDao;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.service.serviceemission.ServiceEmissionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles the logical operations for <code>InstantaneousData</code> objects.
 * @author Alvin Quach
 */
@Service
public class InstantaneousDataService extends ServiceEmissionDataService<InstantaneousData> {

	@Autowired
	InstantaneousDataDao instantaneousDataDao;
	
	@Override
	protected ServiceEmissionDataDao<InstantaneousData> getCrudRepository() {
		return instantaneousDataDao;
	}

}
