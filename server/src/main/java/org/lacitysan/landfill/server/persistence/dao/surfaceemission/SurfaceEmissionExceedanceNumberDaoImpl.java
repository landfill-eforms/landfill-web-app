package org.lacitysan.landfill.server.persistence.dao.surfaceemission;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.SurfaceEmissionExceedanceNumber;
import org.lacitysan.landfill.server.persistence.enums.exceedance.ExceedanceStatus;
import org.lacitysan.landfill.server.persistence.enums.location.Site;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 * @param <T>
 */
public abstract class SurfaceEmissionExceedanceNumberDaoImpl<T extends SurfaceEmissionExceedanceNumber> extends AbstractDaoImpl<T> implements SurfaceEmissionExceedanceNumberDao<T> {

	@Override
	@Transactional
	public List<T> getAllVerified() {
		List<?> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(getGenericClass())
				.add(Restrictions.ne("status", ExceedanceStatus.UNVERIFIED))
				.list();
		return result.stream()
				.map(e -> initialize(checkType(e)))
				.filter(e -> e != null)
				.collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public List<T> getAllUnverified() {
		List<?> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(getGenericClass())
				.add(Restrictions.eq("status", ExceedanceStatus.UNVERIFIED))
				.list();
		return result.stream()
				.map(e -> initialize(checkType(e)))
				.filter(e -> e != null)
				.collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public List<T> getBySiteAndDateCode(Site site, Short dateCode) {
		if (site == null) {
			return new ArrayList<>();
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
	public List<T> getUnverifiedBySiteAndDateCode(Site site, Short dateCode) {
		if (site == null) {
			return new ArrayList<>();
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
	public List<T> getVerifiedBySiteAndDateCode(Site site, Short dateCode) {
		if (site == null) {
			return new ArrayList<>();
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
	
	/** 
	 * Queries the database by site and date-code range.
	 * The start and/or end of the date-code range can be left open-ended by setting the respective parameters to <code>null</code>.
	 * The start and end of the date-code range are both inclusive.
	 * For example, in order to query for a set of data from XX1701-XX through XX1703-XX, 
	 * the values <code>1701</code> and <code>1703</code> should be passed through the start and end parameters, respectively.
	 * @param site The site to query for.
	 * @param start The inclusive start of the date-code range of the data to query for. 
	 * 				Set to <code>null</code> to leave the start of the date-code range open.
	 * @param end The inclusive end of the date-code range of the data to query for. 
	 * 			  Set to <code>null</code> to leave the end of the date-code range open.
	 * @return A list of objects that match the query parameters.
	 */
	@Override
	@Transactional
	public List<T> getVerifiedBySiteAndDateCodeRange(Site site, Short start, Short end) {
		if (site == null) {
			return new ArrayList<>();
		}
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(getGenericClass())
				.add(Restrictions.ne("status", ExceedanceStatus.UNVERIFIED))
				.add(Restrictions.eq("site", site));
		if (start != null) {
			criteria.add(Restrictions.ge("dateCode", start));
		}
		if (end != null) {
			criteria.add(Restrictions.le("dateCode", end));
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
