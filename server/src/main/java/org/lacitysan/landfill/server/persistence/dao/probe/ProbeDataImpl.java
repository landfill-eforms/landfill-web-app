package org.lacitysan.landfill.server.persistence.dao.probe;

import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.probe.ProbeData;
import org.springframework.stereotype.Repository;

@Repository
public class ProbeDataImpl extends AbstractDaoImpl<ProbeData> implements ProbeDataDao{

	@Override
	public ProbeData initialize(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
