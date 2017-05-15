package org.lacitysan.landfill.server.persistence.dao.surfaceemission.instantaneous;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.SurfaceEmissionDataDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * Implemented data access object for <code>InstantaneousData</code> entities.
 * @author Alvin Quach
 */
@Repository
public class InstantaneousDataDaoImpl extends SurfaceEmissionDataDaoImpl<InstantaneousData> implements InstantaneousDataDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Autowired
	MonitoringPointService monitoringPointService;

	@Override
	public InstantaneousData initialize(InstantaneousData instantaneousData) {
		if (instantaneousData == null) {
			return null;
		}
		Hibernate.initialize(instantaneousData.getInstrument());
		Hibernate.initialize(instantaneousData.getMonitoringPoint());
		Hibernate.initialize(instantaneousData.getInspector());
		instantaneousData.getImeNumbers().forEach(imeNumber -> Hibernate.initialize(imeNumber));
		return instantaneousData;
	}

}
