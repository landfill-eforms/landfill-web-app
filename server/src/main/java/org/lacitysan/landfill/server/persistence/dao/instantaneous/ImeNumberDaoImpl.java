package org.lacitysan.landfill.server.persistence.dao.instantaneous;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeData;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.persistence.enums.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class ImeNumberDaoImpl extends AbstractDaoImpl<ImeNumber> implements ImeNumberDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ImeNumber> getBySiteName(String siteName) {
		List<ImeNumber> result = new ArrayList<>();
		Site site = Site.valueOf(siteName);
		if (site != null) {
			result = hibernateTemplate.getSessionFactory().getCurrentSession()
					.createCriteria(ImeNumber.class)
					.add(Restrictions.eq("site", Site.valueOf(siteName)))
					.list();
			result.forEach(imeNumber -> initialize(imeNumber));
			return result;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ImeNumber> getBySiteAndDateCode(Site site, Integer dateCode) {
		List<ImeNumber> result = new ArrayList<>();
		if (site != null) {
			result = hibernateTemplate.getSessionFactory().getCurrentSession()
					.createCriteria(ImeNumber.class)
					.add(Restrictions.eq("site", site))
					.add(Restrictions.eq("dateCode", dateCode))
					.list();
			result.forEach(imeNumber -> initialize(imeNumber));
			return result;
		}
		return null;
	}

	@Override
	@Transactional
	public ImeNumber getByImeNumber(ImeNumber imeNumber) {
		if (imeNumber != null) {
			Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
					.createCriteria(ImeNumber.class)
					.add(Restrictions.eq("site", imeNumber.getSite()))
					.add(Restrictions.eq("dateCode", imeNumber.getDateCode()))
					.add(Restrictions.eq("sequence", imeNumber.getSequence()))
					.uniqueResult();
			if (result instanceof ImeNumber) {
				return initialize((ImeNumber)result);
			}
		}
		return null;
	}

	@Override
	@Transactional
	public ImeNumber update(ImeNumber imeNumber) {
		
		// TODO MOVE THIS
		for (ImeData data : imeNumber.getImeData()) {
			data.setImeNumber(imeNumber);
		}
		
		hibernateTemplate.update(imeNumber);
		return imeNumber;
	}

	public ImeNumber initialize(ImeNumber imeNumber) {
		Hibernate.initialize(imeNumber.getMonitoringPoints());
		imeNumber.getInstantaneousData().forEach(instantaneousData -> {
			Hibernate.initialize(instantaneousData);
		});
		imeNumber.getUnverifiedInstantaneousData().forEach(unverifiedInstantaneousData -> {
			Hibernate.initialize(unverifiedInstantaneousData);
		});
		Hibernate.initialize(imeNumber.getImeData());
		return imeNumber;
	}

}
