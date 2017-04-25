package org.lacitysan.landfill.server.persistence.dao.serviceemission.instantaneous;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.dao.serviceemission.ServiceEmissionDataDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Alvin Quach
 */
@Repository
public class InstantaneousDataDaoImpl extends ServiceEmissionDataDaoImpl<InstantaneousData> implements InstantaneousDataDao {

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
		Hibernate.initialize(instantaneousData.getWarmspotData());
		instantaneousData.getImeNumbers().forEach(imeNumber -> Hibernate.initialize(imeNumber));
		return instantaneousData;
	}

}
