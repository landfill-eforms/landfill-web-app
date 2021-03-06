package org.lacitysan.landfill.server.persistence.dao.unverified;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedInstantaneousData;
import org.springframework.stereotype.Repository;

/**
 * Implemented data access object for <code>UnverifiedInstantaneousData</code> entities.
 * @author Alvin Quach
 */
@Repository
public class UnverifiedInstantaneousDataDaoImpl extends AbstractDaoImpl<UnverifiedInstantaneousData> implements UnverifiedInstantaneousDataDao {

	@Override
	public UnverifiedInstantaneousData initialize(UnverifiedInstantaneousData unverifiedInstantaneousData) {
		if (unverifiedInstantaneousData == null) {
			return null;
		}
		Hibernate.initialize(unverifiedInstantaneousData.getInstrument());
		Hibernate.initialize(unverifiedInstantaneousData.getMonitoringPoint());
		Hibernate.initialize(unverifiedInstantaneousData.getUnverifiedDataSet());
		unverifiedInstantaneousData.getImeNumbers().forEach(imeNumber -> Hibernate.initialize(imeNumber));
		return unverifiedInstantaneousData;
	}

}
