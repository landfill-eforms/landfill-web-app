package org.lacitysan.landfill.server.persistence.dao.instantaneous;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.WarmspotData;
import org.springframework.stereotype.Repository;

/**
 * @author Alvin Quach
 */
@Repository
public class WarmspotDataDaoImpl extends AbstractDaoImpl<WarmspotData> implements WarmspotDataDao {

	@Override
	public WarmspotData initialize(Object entity) {
		if (entity instanceof WarmspotDataDaoImpl) {
			WarmspotData warmspotData = (WarmspotData)entity;
			Hibernate.initialize(warmspotData.getInspector());
			warmspotData.getInstantaneousData().forEach(instantaneousData -> {
				Hibernate.initialize(instantaneousData.getInstrument());
				Hibernate.initialize(instantaneousData.getInspector());
			});
			warmspotData.getUnverifiedInstantaneousData().forEach(instantaneousData -> {
				Hibernate.initialize(instantaneousData.getInstrument());
			});
			return warmspotData;
		}
		return null;
	}

}
