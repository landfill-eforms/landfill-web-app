package org.lacitysan.landfill.server.persistence.dao.instantaneous;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.model.Site;
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
		result.stream().forEach(imeNumber -> {
			initialize(imeNumber);
		});
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<IMENumber> getBySite(String siteName) {
		List<IMENumber> result = new ArrayList<>();
		Site site = Site.valueOf(siteName);
		if (site != null) {
			result = hibernateTemplate.getSessionFactory().getCurrentSession()
					.createCriteria(IMENumber.class)
					.add(Restrictions.eq("site", Site.valueOf(siteName)))
					.list();
					result.stream().forEach(imeNumber -> {
						initialize(imeNumber);
					});
			return result;
		}
		return null;
	}

	@Override
	@Transactional
	public IMENumber getById(Integer id) {
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(IMENumber.class)
				.add(Restrictions.idEq(id))
				.uniqueResult();
		if (result instanceof IMENumber) {
			return initialize((IMENumber)result);
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
	
	private IMENumber initialize(IMENumber imeNumber) {
		Hibernate.initialize(imeNumber.getMonitoringPoints());
		Hibernate.initialize(imeNumber.getInstantaneousData());
		Hibernate.initialize(imeNumber.getUnverifiedInstantaneousData());
		Hibernate.initialize(imeNumber.getImeData());
		Hibernate.initialize(imeNumber.getImeRepairData());
		return imeNumber;
	}

}
