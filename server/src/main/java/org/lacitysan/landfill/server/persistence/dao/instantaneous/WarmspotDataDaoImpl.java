package org.lacitysan.landfill.server.persistence.dao.instantaneous;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.WarmspotData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class WarmspotDataDaoImpl implements WarmspotDataDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<WarmspotData> getAllWarmspots() {
		List<WarmspotData> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(WarmspotData.class)
				.list();
		result.stream().forEach(warmspotData -> {
			initialize(warmspotData);
		});
		return result;
	}
	
	@Override
	@Transactional
	public WarmspotData getWarmspotById(Integer id) {
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(WarmspotData.class)
				.add(Restrictions.idEq(id))
				.uniqueResult();
		if (result instanceof WarmspotData) {
			WarmspotData warmspotData = (WarmspotData)result;
			return initialize(warmspotData);
		}
		return null;
	}
	
	@Override
	@Transactional
	public Object update(WarmspotData warmspotData) {
		hibernateTemplate.update(warmspotData);
		return true;
	}
	
	@Override
	@Transactional
	public Object create(WarmspotData warmspotData) {
		return hibernateTemplate.save(warmspotData);
	}
	
	@Override
	@Transactional
	public Object delete(WarmspotData warmspotData) {
		hibernateTemplate.delete(warmspotData);
		return true;
	}
	
	private WarmspotData initialize(WarmspotData warmspotData) {
		Hibernate.initialize(warmspotData.getInspector());
		warmspotData.getInstantaneousData().stream().forEach(instantaneousData -> {
			Hibernate.initialize(instantaneousData.getInstrument());
			Hibernate.initialize(instantaneousData.getInspector());
		});
		warmspotData.getUnverifiedInstantaneousData().stream().forEach(instantaneousData -> {
			Hibernate.initialize(instantaneousData.getInstrument());
		});
		return warmspotData;
	}
	
}
