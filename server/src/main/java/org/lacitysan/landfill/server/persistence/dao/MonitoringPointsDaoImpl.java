package org.lacitysan.landfill.server.persistence.dao;

import java.util.List;

import org.lacitysan.landfill.lib.enumeration.Site;
import org.lacitysan.landfill.server.persistence.entity.MonitoringPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class MonitoringPointsDaoImpl implements MonitoringPointsDao {
	
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	@Transactional
	public MonitoringPoint getBySiteAndMonitoringPointName(Site site, String monitoringPointName) {
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("from MonitoringPoint where site=:site and monitoringPointName=:monitoringPointName")
				.setParameter("site", site)
				.setParameter("monitoringPointName", monitoringPointName)
				.uniqueResult();
		if (result instanceof MonitoringPoint) {
			return (MonitoringPoint)result;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<MonitoringPoint> getAllMonitoringPoints() {
		return hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(MonitoringPoint.class)
				.list();
	}

}
