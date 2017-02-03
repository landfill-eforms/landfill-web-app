package org.lacitysan.landfill.server.persistence.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.lib.enumeration.Site;
import org.lacitysan.landfill.server.persistence.entity.InstantaneousData;
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

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<InstantaneousData> getBySite(String siteName) {
		List<InstantaneousData> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery("from InstantaneousData where monitoringPoint.site=:site")
				.setParameter("site", Site.valueOf(siteName))
				.list();
		result.stream().forEach(data -> {
			Hibernate.initialize(data.getInstrument());
			Hibernate.initialize(data.getMonitoringPoint());
			Hibernate.initialize(data.getUser().getPerson());
		});
		return result;
	}

}
