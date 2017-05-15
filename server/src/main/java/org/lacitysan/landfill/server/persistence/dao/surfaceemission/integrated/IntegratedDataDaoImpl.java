package org.lacitysan.landfill.server.persistence.dao.surfaceemission.integrated;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.dao.surfaceemission.SurfaceEmissionDataDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.integrated.IntegratedData;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * Implemented data access object for <code>IntegratedData</code> entities.
 * @author Alvin Quach
 */
@Repository
public class IntegratedDataDaoImpl extends SurfaceEmissionDataDaoImpl<IntegratedData> implements IntegratedDataDao {

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
