package org.lacitysan.landfill.server.persistence.dao.serviceemission.integrated;

import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.integrated.IseData;
import org.springframework.stereotype.Repository;

/**
 * @author Alvin Quach
 */
@Repository
public class IseDataDaoImpl extends AbstractDaoImpl<IseData> implements IseDataDao {

	@Override
	public IseData initialize(IseData iseData) {
		if (iseData == null) {
			return null;
		}
		// TODO Implement this.
		return iseData;
	}
	
}
