package org.lacitysan.landfill.server.persistence.dao.instantaneous;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.persistence.enums.MonitoringPoint;
import org.lacitysan.landfill.server.persistence.enums.MonitoringPointType;
import org.lacitysan.landfill.server.persistence.enums.Site;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.lacitysan.landfill.server.util.model.IntegerRange;
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
		for (IntegerRange range : monitoringPointService.getRanges(MonitoringPointType.GRID, Site.valueOf(siteName))) {
			result.addAll(hibernateTemplate.getSessionFactory().getCurrentSession()
					.createCriteria(InstantaneousData.class)
					.add(Restrictions.ge("monitoringPoint", monitoringPoints[range.getMin()]))
					.add(Restrictions.le("monitoringPoint", monitoringPoints[range.getMax()]))
					.list());
		}
		result.forEach(data -> initialize(data));
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<InstantaneousData> getBySiteAndDate(String siteName, Long start, Long end) {
		List<InstantaneousData> result = new ArrayList<>();
		MonitoringPoint[] monitoringPoints = MonitoringPoint.values();
		for (IntegerRange range : monitoringPointService.getRanges(MonitoringPointType.GRID, Site.valueOf(siteName))) {
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
		result.forEach(data -> initialize(data));
		return result;
	}
	
	@Override
	@Transactional
	public Object create(InstantaneousData data) {
		return hibernateTemplate.save(data);
	}
	
	private InstantaneousData initialize(InstantaneousData instantaneousData) {
		Hibernate.initialize(instantaneousData.getInstrument());
		Hibernate.initialize(instantaneousData.getMonitoringPoint());
		Hibernate.initialize(instantaneousData.getInspector());
		instantaneousData.getImeNumbers().forEach(imeNumber -> Hibernate.initialize(imeNumber));
		instantaneousData.getWarmspotData().forEach(warmspot -> Hibernate.initialize(warmspot));
		return instantaneousData;
	}

}
