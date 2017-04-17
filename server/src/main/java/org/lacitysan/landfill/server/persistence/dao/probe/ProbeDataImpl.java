package org.lacitysan.landfill.server.persistence.dao.probe;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.probe.ProbeData;
import org.springframework.stereotype.Repository;

/**
 * @author Alvin Quach
 */
@Repository
public class ProbeDataImpl extends AbstractDaoImpl<ProbeData> implements ProbeDataDao {

	@Override
	public ProbeData initialize(ProbeData probeData) {
		if (probeData == null) {
			return null;
		}
		probeData.getInspectors().forEach(inspector -> Hibernate.initialize(inspector));
		return probeData;
	}

}
