package org.lacitysan.landfill.server.persistence.dao.instantaneous;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeData;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeNumber;
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
public class ImeNumberDaoImpl extends AbstractDaoImpl<ImeNumber> implements ImeNumberDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	@Transactional
	public List<ImeNumber> getBySiteAndDateCode(Site site, Integer dateCode) {
		if (site == null) {
			return null;
		}
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(ImeNumber.class)
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
	public List<ImeNumber> getUnverifiedBySiteAndDateCode(Site site, Integer dateCode) {
		if (site == null) {
			return null;
		}
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(ImeNumber.class)
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
	public List<ImeNumber> getVerifiedBySiteAndDateCode(Site site, Integer dateCode) {
		if (site == null) {
			return null;
		}
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(ImeNumber.class)
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
	public ImeNumber getByImeNumber(ImeNumber imeNumber) {
		if (imeNumber == null) {
			return null;
		}
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(ImeNumber.class)
				.add(Restrictions.eq("site", imeNumber.getSite()))
				.add(Restrictions.eq("dateCode", imeNumber.getDateCode()))
				.add(Restrictions.eq("sequence", imeNumber.getSequence()))
				.uniqueResult();
		return initialize(checkType(result));
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

	@Override
	public ImeNumber initialize(ImeNumber imeNumber) {
		if (imeNumber == null) {
			return null;
		}
		Hibernate.initialize(imeNumber.getMonitoringPoints());
		imeNumber.getInstantaneousData().forEach(instantaneousData -> {
			Hibernate.initialize(instantaneousData);
		});
		imeNumber.getUnverifiedInstantaneousData().forEach(unverifiedInstantaneousData -> {
			Hibernate.initialize(unverifiedInstantaneousData);
		});
		imeNumber.getImeData().forEach(imeData -> {
			imeData.getImeRepairData().forEach(imeRepairData -> {
				Hibernate.initialize(imeRepairData.getUser());
			});
		});
		return imeNumber;
	}

}
