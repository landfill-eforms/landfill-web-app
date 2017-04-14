package org.lacitysan.landfill.server.persistence.dao.integrated;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.integrated.IseData;
import org.lacitysan.landfill.server.persistence.entity.integrated.IseNumber;
import org.lacitysan.landfill.server.persistence.enums.ExceedanceStatus;
import org.lacitysan.landfill.server.persistence.enums.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class IseNumberDaoImpl extends AbstractDaoImpl<IseNumber> implements IseNumberDao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<IseNumber> getBySiteName(String siteName) {
		List<IseNumber> result = new ArrayList<>();
		Site site = Site.valueOf(siteName);
		if (site != null) {
			result = hibernateTemplate.getSessionFactory().getCurrentSession()
					.createCriteria(IseNumber.class)
					.add(Restrictions.eq("site", Site.valueOf(siteName)))
					.list();
			result.stream().map(iseNumber -> initialize(iseNumber)).filter(iseNumber -> iseNumber != null).collect(Collectors.toList());
			return result;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<IseNumber> getBySiteAndDateCode(Site site, Integer dateCode) {
		List<IseNumber> result = new ArrayList<>();
		if (site != null) {
			result = hibernateTemplate.getSessionFactory().getCurrentSession()
					.createCriteria(IseNumber.class)
					.add(Restrictions.eq("site", site))
					.add(Restrictions.eq("dateCode", dateCode))
					.list();
			result.stream().map(iseNumber -> initialize(iseNumber)).filter(iseNumber -> iseNumber != null).collect(Collectors.toList());
			return result;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<IseNumber> getUnverifiedBySiteAndDateCode(Site site, Integer dateCode) {
		List<IseNumber> result = new ArrayList<>();
		if (site != null) {
			result = hibernateTemplate.getSessionFactory().getCurrentSession()
					.createCriteria(IseNumber.class)
					.add(Restrictions.eq("site", site))
					.add(Restrictions.eq("dateCode", dateCode))
					.add(Restrictions.eq("status", ExceedanceStatus.UNVERIFIED))
					.list();
			result.stream().map(imeNumber -> initialize(imeNumber)).filter(imeNumber -> imeNumber != null).collect(Collectors.toList());
			return result;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<IseNumber> getVerifiedBySiteAndDateCode(Site site, Integer dateCode) {
		List<IseNumber> result = new ArrayList<>();
		if (site != null) {
			result = hibernateTemplate.getSessionFactory().getCurrentSession()
					.createCriteria(IseNumber.class)
					.add(Restrictions.eq("site", site))
					.add(Restrictions.eq("dateCode", dateCode))
					.add(Restrictions.ne("status", ExceedanceStatus.UNVERIFIED))
					.list();
			result.stream().map(imeNumber -> initialize(imeNumber)).filter(imeNumber -> imeNumber != null).collect(Collectors.toList());
			return result;
		}
		return null;
	}

	@Override
	@Transactional
	public IseNumber getByIseNumber(IseNumber iseNumber) {
		if (iseNumber != null) {
			Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
					.createCriteria(IseNumber.class)
					.add(Restrictions.eq("site", iseNumber.getSite()))
					.add(Restrictions.eq("dateCode", iseNumber.getDateCode()))
					.add(Restrictions.eq("sequence", iseNumber.getSequence()))
					.uniqueResult();
			return initialize(result);
		}
		return null;
	}

	@Override
	@Transactional
	public IseNumber update(IseNumber iseNumber) {
		
		// TODO MOVE THIS
		for (IseData data : iseNumber.getIseData()) {
			data.setIseNumber(iseNumber);
		}
		
		hibernateTemplate.update(iseNumber);
		return iseNumber;
	}

	@Override
	public IseNumber initialize(Object entity) {
		if (entity instanceof IseNumber) {
			IseNumber iseNumber = (IseNumber)entity;
			Hibernate.initialize(iseNumber.getMonitoringPoints());
			Hibernate.initialize(iseNumber.getIseData());
			return iseNumber;
		}
		return null;
	}
	
}
