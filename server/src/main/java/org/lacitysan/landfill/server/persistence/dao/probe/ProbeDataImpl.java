package org.lacitysan.landfill.server.persistence.dao.probe;

import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.probe.ProbeData;
import org.springframework.stereotype.Repository;

/**
 * @author Alvin Quach
 */
@Repository
public class ProbeDataImpl extends AbstractDaoImpl<ProbeData> implements ProbeDataDao {

	@Override
	public ProbeData initialize(Object entity) {
		if (entity instanceof ProbeData) {
			return (ProbeData)entity;
		}
		return null;
	}

}
