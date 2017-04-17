package org.lacitysan.landfill.server.persistence.dao.integrated;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.integrated.IntegratedData;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Fuck you Allen, this is how its done.
 * @author Alvin Quach
 */
@Repository
public class IntegratedDataDaoImpl extends AbstractDaoImpl<IntegratedData> implements IntegratedDataDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Autowired
	MonitoringPointService monitoringPointService;

	@Override
	@Transactional
	public List<IntegratedData> getBySite(String siteName) {
		List<IntegratedData> result = new ArrayList<>();
		// TODO Implement this.
		return result;
	}

	@Override
	@Transactional
	public List<IntegratedData> getBySiteAndDate(String siteName, Long start, Long end) {
		List<IntegratedData> result = new ArrayList<>();
		// TODO Implement this.
		return result;
	}

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
