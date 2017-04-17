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
	public WarmspotData initialize(WarmspotData warmspotData) {
		if (warmspotData == null) {
			return null;
		}
		Hibernate.initialize(warmspotData.getInspector());
		warmspotData.getInstantaneousData().forEach(instantaneousData -> {
			Hibernate.initialize(instantaneousData.getInstrument());
			Hibernate.initialize(instantaneousData.getInspector());
		});
		return warmspotData;
	}

}
