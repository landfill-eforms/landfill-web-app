package org.lacitysan.landfill.server.persistence.dao.integrated;

import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.integrated.IseData;
import org.springframework.stereotype.Repository;

@Repository
public class IntegratedDataDaoImpl extends AbstractDaoImpl<IseData> implements IseDataDao{

	@Override
	public IseData initialize(Object entity) {
		if (entity instanceof IseData) {
			return (IseData)entity;
		}
		return null;
	}

}
