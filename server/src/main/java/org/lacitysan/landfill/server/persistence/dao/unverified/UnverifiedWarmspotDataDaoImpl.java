package org.lacitysan.landfill.server.persistence.dao.unverified;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedWarmspotData;
import org.springframework.stereotype.Repository;

/**
 * @author Alvin Quach
 */
@Repository
public class UnverifiedWarmspotDataDaoImpl extends AbstractDaoImpl<UnverifiedWarmspotData> implements UnverifiedWarmspotDataDao {

	@Override
	public UnverifiedWarmspotData initialize(UnverifiedWarmspotData unverifiedWarmspotData) {
		if (unverifiedWarmspotData == null) {
			return null;
		}
		Hibernate.initialize(unverifiedWarmspotData.getInstrument());
		Hibernate.initialize(unverifiedWarmspotData.getMonitoringPoint());
		Hibernate.initialize(unverifiedWarmspotData.getUnverifiedDataSet());
		return unverifiedWarmspotData;
	}

}
