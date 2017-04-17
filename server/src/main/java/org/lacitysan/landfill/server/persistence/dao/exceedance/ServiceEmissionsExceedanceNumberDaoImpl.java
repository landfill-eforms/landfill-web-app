package org.lacitysan.landfill.server.persistence.dao.exceedance;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.exceedance.ServiceEmissionsExceedanceNumber;
import org.lacitysan.landfill.server.persistence.enums.exceedance.ExceedanceStatus;
import org.lacitysan.landfill.server.persistence.enums.location.Site;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 * @param <T>
 */
public abstract class ServiceEmissionsExceedanceNumberDaoImpl<T extends ServiceEmissionsExceedanceNumber> extends AbstractDaoImpl<T> implements ServiceEmissionsExceedanceNumberDao<T> {

	@Override
	@Transactional
	public List<T> getBySiteAndDateCode(Site site, Integer dateCode) {
		if (site == null) {
			return null;
		}
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(getGenericClass())
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
	public List<T> getUnverifiedBySiteAndDateCode(Site site, Integer dateCode) {
		if (site == null) {
			return null;
		}
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(getGenericClass())
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
	public List<T> getVerifiedBySiteAndDateCode(Site site, Integer dateCode) {
		if (site == null) {
			return null;
		}
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(getGenericClass())
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

	@Transactional
	protected T getByExceedanceNumber(T exceedanceNumber) {
		if (exceedanceNumber == null) {
			return null;
		}
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(getGenericClass())
				.add(Restrictions.eq("site", exceedanceNumber.getSite()))
				.add(Restrictions.eq("dateCode", exceedanceNumber.getDateCode()))
				.add(Restrictions.eq("sequence", exceedanceNumber.getSequence()))
				.uniqueResult();
		return initialize(checkType(result));
	}
	
}
