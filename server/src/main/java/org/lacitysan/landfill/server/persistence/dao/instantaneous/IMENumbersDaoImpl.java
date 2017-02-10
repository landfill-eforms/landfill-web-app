package org.lacitysan.landfill.server.persistence.dao.instantaneous;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.IMENumber;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class IMENumbersDaoImpl implements IMENumbersDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Autowired
	MonitoringPointService monitoringPointService;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<IMENumber> getAll() {
		List<IMENumber> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(IMENumber.class)
				.list();
		return result;
	}
	
	@Override
	@Transactional
	public IMENumber getById(Integer id) {
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(IMENumber.class)
				.add(Restrictions.idEq(id))
				.uniqueResult();
		if (result instanceof IMENumber) {
			IMENumber data = (IMENumber)result;
			return data;
		}
		return null;
	}
	
	@Override
	@Transactional
	public Object update(IMENumber imeNumber) {
		hibernateTemplate.update(imeNumber);
		return true;
	}

	@Override
	@Transactional
	public Object create(IMENumber imeNumber) {
		return hibernateTemplate.save(imeNumber);
	}
	
}
