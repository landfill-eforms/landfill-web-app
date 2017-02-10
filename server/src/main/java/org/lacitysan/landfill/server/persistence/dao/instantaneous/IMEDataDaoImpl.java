package org.lacitysan.landfill.server.persistence.dao.instantaneous;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.IMEData;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class IMEDataDaoImpl implements IMEDataDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Autowired
	MonitoringPointService monitoringPointService;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<IMEData> getAll() {
		List<IMEData> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(IMEData.class)
				.list();
		return result;
	}
	
	@Override
	@Transactional
	public IMEData getById(Integer id) {
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(IMEData.class)
				.add(Restrictions.idEq(id))
				.uniqueResult();
		if (result instanceof IMEData) {
			IMEData data = (IMEData)result;
			return data;
		}
		return null;
	}
	
	@Override
	@Transactional
	public Object update(IMEData imeNumber) {
		hibernateTemplate.update(imeNumber);
		return true;
	}

	@Override
	@Transactional
	public Object create(IMEData imeNumber) {
		return hibernateTemplate.save(imeNumber);
	}
	
}
