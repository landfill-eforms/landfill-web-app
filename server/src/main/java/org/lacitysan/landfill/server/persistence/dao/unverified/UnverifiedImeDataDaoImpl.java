package org.lacitysan.landfill.server.persistence.dao.unverified;

import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedImeData;
import org.springframework.stereotype.Repository;

/**
 * @author Alvin Quach
 */
@Repository
public class UnverifiedImeDataDaoImpl extends AbstractDaoImpl<UnverifiedImeData> implements UnverifiedImeDataDao {

	@Override
	public UnverifiedImeData initialize(UnverifiedImeData unverifiedImeData) {
		return unverifiedImeData;
	}

	
}
