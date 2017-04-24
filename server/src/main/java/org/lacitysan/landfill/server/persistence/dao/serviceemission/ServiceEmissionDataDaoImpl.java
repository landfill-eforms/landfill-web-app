package org.lacitysan.landfill.server.persistence.dao.serviceemission;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.ServiceEmissionData;
import org.lacitysan.landfill.server.persistence.enums.location.Site;
import org.lacitysan.landfill.server.util.DateTimeUtils;
import org.springframework.transaction.annotation.Transactional;

public abstract class ServiceEmissionDataDaoImpl<T extends ServiceEmissionData> extends AbstractDaoImpl<T> implements ServiceEmissionDataDao<T> {

	@Override
	@Transactional
	public List<T> getBySiteAndDate(Site site, Long start, Long end) {
		if (site == null) {
			return new ArrayList<>();
		}
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(getGenericClass());
		if (start != null) {
			criteria.add(Restrictions.ge("startTime", DateTimeUtils.longToSqlDate(start)));
		}
		if (end != null) {
			criteria.add(Restrictions.lt("endTime", DateTimeUtils.longToSqlDate(DateTimeUtils.addDay(end))));
		}
		List<?> result = criteria.list();
		return result.stream()
				.map(e -> checkType(e))
				.filter(e -> e != null && e.getMonitoringPoint().getSite() == site) // Filter by sites.
				.map(e -> initialize(e))
				.collect(Collectors.toList());
	}
	
}
