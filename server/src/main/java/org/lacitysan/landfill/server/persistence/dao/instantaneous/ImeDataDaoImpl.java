package org.lacitysan.landfill.server.persistence.dao.instantaneous;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeData;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class ImeDataDaoImpl implements ImeDataDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Autowired
	MonitoringPointService monitoringPointService;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ImeData> getAll() {
		List<ImeData> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(ImeData.class)
				.list();
		return result;
	}
	
	@Override
	@Transactional
	public ImeData getById(Integer id) {
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(ImeData.class)
				.add(Restrictions.idEq(id))
				.uniqueResult();
		if (result instanceof ImeData) {
			ImeData data = (ImeData)result;
			return data;
		}
		return null;
	}
	
	@Override
	@Transactional
	public Object update(ImeData imeNumber) {
		hibernateTemplate.update(imeNumber);
		return true;
	}

	@Override
	@Transactional
	public Object create(ImeData imeNumber) {
		return hibernateTemplate.save(imeNumber);
	}
	
}
