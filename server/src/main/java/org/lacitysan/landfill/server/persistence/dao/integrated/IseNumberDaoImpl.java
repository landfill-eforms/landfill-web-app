package org.lacitysan.landfill.server.persistence.dao.integrated;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.integrated.IseData;
import org.lacitysan.landfill.server.persistence.entity.integrated.IseNumber;
import org.lacitysan.landfill.server.persistence.enums.exceedance.ExceedanceStatus;
import org.lacitysan.landfill.server.persistence.enums.location.Site;
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

	@Override
	@Transactional
	public List<IseNumber> getBySiteAndDateCode(Site site, Integer dateCode) {
		if (site == null) {
			return null;
		}
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(IseNumber.class)
				.add(Restrictions.eq("site", site));
		if (dateCode != null) {
			criteria.add(Restrictions.eq("dateCode", dateCode));
		}
		List<?> result = criteria.list();
		return result.stream()
				.map(e -> initialize(checkType(e)))
				.filter(e -> e != null)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<IseNumber> getUnverifiedBySiteAndDateCode(Site site, Integer dateCode) {
		if (site == null) {
			return null;
		}
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(IseNumber.class)
				.add(Restrictions.eq("status", ExceedanceStatus.UNVERIFIED))
				.add(Restrictions.eq("site", site));
		if (dateCode != null) {
			criteria.add(Restrictions.eq("dateCode", dateCode));
		}
		List<?> result = criteria.list();
		return result.stream()
				.map(e -> initialize(checkType(e)))
				.filter(e -> e != null)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<IseNumber> getVerifiedBySiteAndDateCode(Site site, Integer dateCode) {
		if (site == null) {
			return null;
		}
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(IseNumber.class)
				.add(Restrictions.ne("status", ExceedanceStatus.UNVERIFIED))
				.add(Restrictions.eq("site", site));
		if (dateCode != null) {
			criteria.add(Restrictions.eq("dateCode", dateCode));
		}
		List<?> result = criteria.list();
		return result.stream()
				.map(e -> initialize(checkType(e)))
				.filter(e -> e != null)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public IseNumber getByIseNumber(IseNumber iseNumber) {
		if (iseNumber == null) {
			return null;
		}
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(IseNumber.class)
				.add(Restrictions.eq("site", iseNumber.getSite()))
				.add(Restrictions.eq("dateCode", iseNumber.getDateCode()))
				.add(Restrictions.eq("sequence", iseNumber.getSequence()))
				.uniqueResult();
		return initialize(checkType(result));
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
	public IseNumber initialize(IseNumber iseNumber) {
		if (iseNumber == null) {
			return null;
		}
		Hibernate.initialize(iseNumber.getMonitoringPoints());
		iseNumber.getIseData().forEach(iseData -> {
			iseData.getIseRepairData().forEach(imeRepairData -> {
				Hibernate.initialize(imeRepairData.getUser());
			});
		});
		return iseNumber;
	}

}
