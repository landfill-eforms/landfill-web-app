package org.lacitysan.landfill.server.persistence.dao.unverified;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedImeData;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class UnverifiedImeDataDaoImpl implements UnverifiedImeDataDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Autowired
	MonitoringPointService monitoringPointService;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<UnverifiedImeData> getAll() {
		List<UnverifiedImeData> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(UnverifiedImeData.class)
				.list();
		return result;
	}
	
	@Override
	@Transactional
	public UnverifiedImeData getById(Integer id) {
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(ImeData.class)
				.add(Restrictions.idEq(id))
				.uniqueResult();
		if (result instanceof ImeData) {
			UnverifiedImeData data = (UnverifiedImeData)result;
			return data;
		}
		return null;
	}

	@Override
	@Transactional
	public Object update(UnverifiedImeData imeNumber) {
		hibernateTemplate.update(imeNumber);
		return true;
	}

	@Override
	@Transactional
	public Object create(UnverifiedImeData imeNumber) {
		return hibernateTemplate.save(imeNumber);
	}
	
}
