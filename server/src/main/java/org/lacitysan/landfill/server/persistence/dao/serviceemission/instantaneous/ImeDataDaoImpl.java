package org.lacitysan.landfill.server.persistence.dao.serviceemission.instantaneous;

import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous.ImeData;
import org.springframework.stereotype.Repository;

/**
 * @author Alvin Quach
 */
@Repository
public class ImeDataDaoImpl extends AbstractDaoImpl<ImeData> implements ImeDataDao {

	@Override
	public ImeData initialize(ImeData imeData) {
		if (imeData == null) {
			return null;
		}
		// TODO Implement this.
		return imeData;
	}
	
}
