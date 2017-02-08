package org.lacitysan.landfill.server.persistence.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.model.MonitoringPoint;
import org.lacitysan.landfill.server.model.Site;
import org.lacitysan.landfill.server.persistence.entity.InstantaneousData;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.lacitysan.landfill.server.service.model.OrdinalRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class InstantaneousDataDaoImpl implements InstantaneousDataDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Autowired
	MonitoringPointService monitoringPointService;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<InstantaneousData> getBySite(String siteName) {
		List<InstantaneousData> result = new ArrayList<>();
		MonitoringPoint[] monitoringPoints = MonitoringPoint.values();
		for (OrdinalRange range : monitoringPointService.getGridsBySite(Site.valueOf(siteName))) {
			result.addAll(hibernateTemplate.getSessionFactory().getCurrentSession()
					.createCriteria(InstantaneousData.class)
					.add(Restrictions.ge("monitoringPoint", monitoringPoints[range.getMin()]))
					.add(Restrictions.le("monitoringPoint", monitoringPoints[range.getMax()]))
					.list());
		}
		result.stream().forEach(data -> {
			Hibernate.initialize(data.getInstrument());
			Hibernate.initialize(data.getMonitoringPoint());
			Hibernate.initialize(data.getUser().getPerson());
		});
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<InstantaneousData> getBySiteAndDate(String siteName, Long start, Long end) {
		List<InstantaneousData> result = new ArrayList<>();
		MonitoringPoint[] monitoringPoints = MonitoringPoint.values();
		for (OrdinalRange range : monitoringPointService.getGridsBySite(Site.valueOf(siteName))) {
			Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession()
					.createCriteria(InstantaneousData.class)
					.add(Restrictions.ge("monitoringPoint", monitoringPoints[range.getMin()]))
					.add(Restrictions.le("monitoringPoint", monitoringPoints[range.getMax()]));
			if (start >= 0) {
				criteria.add(Restrictions.ge("startTime", new Date(start)));
			}
			if (end >= 0) {
				criteria.add(Restrictions.le("endTime", new Date(end)));
			}
			result.addAll(criteria.list());
		}
		result.stream().forEach(data -> {
			Hibernate.initialize(data.getInstrument());
			Hibernate.initialize(data.getMonitoringPoint());
			Hibernate.initialize(data.getUser().getPerson());
		});
		return result;
	}

}
