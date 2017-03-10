package org.lacitysan.landfill.server.persistence.dao.instantaneous;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeData;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.persistence.enums.Site;
import org.lacitysan.landfill.server.service.ImeService;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class ImeNumberDaoImpl implements ImeNumberDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Autowired
	ImeService imeService;

	@Autowired
	MonitoringPointService monitoringPointService;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ImeNumber> getAll() {
		List<ImeNumber> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(ImeNumber.class)
				.list();
		result.stream().forEach(imeNumber -> {
			initialize(imeNumber);
		});
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ImeNumber> getBySite(String siteName) {
		List<ImeNumber> result = new ArrayList<>();
		Site site = Site.valueOf(siteName);
		if (site != null) {
			result = hibernateTemplate.getSessionFactory().getCurrentSession()
					.createCriteria(ImeNumber.class)
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
	public ImeNumber getByImeNumber(String imeNumber) {
		ImeNumber temp = imeService.getImeNumberFromString(imeNumber);
		if (temp != null) {
			Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
					.createCriteria(ImeNumber.class)
					.add(Restrictions.eq("site", temp.getSite()))
					.add(Restrictions.eq("dateCode", temp.getDateCode()))
					.add(Restrictions.eq("sequence", temp.getSequence()))
					.uniqueResult();
			if (result instanceof ImeNumber) {
				return initialize((ImeNumber)result);
			}
		}
		return null;
	}

	@Override
	@Transactional
	public Object update(ImeNumber imeNumber) {
		
		// TODO MOVE THIS
		for (ImeData data : imeNumber.getImeData() ) {
			data.setImeNumber(imeNumber);
		}
		
		hibernateTemplate.update(imeNumber);
		return true;
	}

	@Override
	@Transactional
	public Object create(ImeNumber imeNumber) {
		return hibernateTemplate.save(imeNumber);
	}

	private ImeNumber initialize(ImeNumber imeNumber) {
		Hibernate.initialize(imeNumber.getMonitoringPoints());
		Hibernate.initialize(imeNumber.getInstantaneousData());
		Hibernate.initialize(imeNumber.getUnverifiedInstantaneousData());
		Hibernate.initialize(imeNumber.getImeData());
//		Hibernate.initialize(imeNumber.getImeRepairData());
		return imeNumber;
	}

}
