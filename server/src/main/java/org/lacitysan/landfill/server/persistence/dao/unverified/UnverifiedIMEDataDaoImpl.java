package org.lacitysan.landfill.server.persistence.dao.unverified;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.IMEData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedIMEData;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class UnverifiedIMEDataDaoImpl implements UnverifiedIMEDataDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Autowired
	MonitoringPointService monitoringPointService;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<UnverifiedIMEData> getAll() {
		List<UnverifiedIMEData> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(UnverifiedIMEData.class)
				.list();
		return result;
	}
	
	@Override
	@Transactional
	public UnverifiedIMEData getById(Integer id) {
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(IMEData.class)
				.add(Restrictions.idEq(id))
				.uniqueResult();
		if (result instanceof IMEData) {
			UnverifiedIMEData data = (UnverifiedIMEData)result;
			return data;
		}
		return null;
	}

	@Override
	@Transactional
	public Object update(UnverifiedIMEData imeNumber) {
		hibernateTemplate.update(imeNumber);
		return true;
	}

	@Override
	@Transactional
	public Object create(UnverifiedIMEData imeNumber) {
		return hibernateTemplate.save(imeNumber);
	}
	
}
