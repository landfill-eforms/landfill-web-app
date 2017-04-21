package org.lacitysan.landfill.server.persistence.dao.serviceemission.integrated;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.integrated.IntegratedData;
import org.lacitysan.landfill.server.persistence.enums.location.Site;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.lacitysan.landfill.server.util.DateTimeUtils;
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
	public List<IntegratedData> getBySite(Site site) {
		List<?> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(InstantaneousData.class)
				.list();
		return result.stream()
				.map(e -> checkType(e))
				.filter(e -> e != null && e.getMonitoringPoint().getSite() == site) // Filter by site.
				.map(e -> initialize(e))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<IntegratedData> getBySiteAndDate(Site site, Long start, Long end) {
		List<?> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(InstantaneousData.class)
				.add(Restrictions.ge("startTime", DateTimeUtils.longToSqlDate(start)))
				.add(Restrictions.lt("endTime", DateTimeUtils.longToSqlDate(DateTimeUtils.addDay(end))))
				.list();
		return result.stream()
				.map(e -> checkType(e))
				.filter(e -> e != null && e.getMonitoringPoint().getSite() == site) // Filter by site.
				.map(e -> initialize(e))
				.collect(Collectors.toList());
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
