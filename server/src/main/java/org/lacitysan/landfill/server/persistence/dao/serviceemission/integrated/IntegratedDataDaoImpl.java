package org.lacitysan.landfill.server.persistence.dao.serviceemission.integrated;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.dao.serviceemission.ServiceEmissionDataDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.integrated.IntegratedData;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * Fuck you Allen, this is how its done.
 * @author Alvin Quach
 */
@Repository
public class IntegratedDataDaoImpl extends ServiceEmissionDataDaoImpl<IntegratedData> implements IntegratedDataDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Autowired
	MonitoringPointService monitoringPointService;

	@Override
	public IntegratedData initialize(IntegratedData integratedData) {
		if (integratedData == null) {
			return null;
		}
		Hibernate.initialize(integratedData.getInstrument());
		Hibernate.initialize(integratedData.getMonitoringPoint());
		Hibernate.initialize(integratedData.getInspector());
		return integratedData;
	}
}
