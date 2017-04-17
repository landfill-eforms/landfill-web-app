package org.lacitysan.landfill.server.persistence.dao.instantaneous;

import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeData;
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
