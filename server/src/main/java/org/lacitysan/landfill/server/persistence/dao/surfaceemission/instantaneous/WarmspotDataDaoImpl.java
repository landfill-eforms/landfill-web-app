package org.lacitysan.landfill.server.persistence.dao.surfaceemission.instantaneous;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.instantaneous.WarmspotData;
import org.lacitysan.landfill.server.persistence.enums.location.Site;
import org.lacitysan.landfill.server.util.DateTimeUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class WarmspotDataDaoImpl extends AbstractDaoImpl<WarmspotData> implements WarmspotDataDao {

	/** 
	 * Queries the database by site and date range.
	 * The start and/or end of the date range can be left open-ended by setting the respective parameters to <code>null</code>.
	 * The start of the date range is inclusive, and the end of the date range is exclusive.
	 * For example, in order to query for data from 1/31/17 through 3/31/17, 
	 * the long values for 1/31/17 and 4/1/17 should be passed through the start and end parameters, respectively.
	 * The values are automatically normalized by setting the hours, minutes, seconds, and milliseconds to zero.
	 * Time zone conversions are also handled automatically.
	 * @param site The site to query for.
	 * @param start The inclusive start of the date range of the data to query for. 
	 * 				Set to <code>null</code> to leave the start of the date range open.
	 * @param end The exclusive end of the date range of the data to query for. 
	 * 			  Set to <code>null</code> to leave the end of the date range open.
	 * @return A list of objects that match the query parameters.
	 */
	@Override
	@Transactional
	public List<WarmspotData> getBySiteAndDate(Site site, Long start, Long end) {
		if (site == null) {
			return new ArrayList<>();
		}
		Criteria criteria = hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(WarmspotData.class);
		if (start != null) {
			criteria.add(Restrictions.ge("date", DateTimeUtils.longToSqlDate(start)));
		}
		if (end != null) {
			criteria.add(Restrictions.lt("date", DateTimeUtils.longToSqlDate(end)));
		}
		List<?> result = criteria.list();
		return result.stream()
				.map(e -> checkType(e))
				.filter(e -> e != null && e.getMonitoringPoint().getSite() == site) // Filter by sites.
				.map(e -> initialize(e))
				.collect(Collectors.toList());
	}
	
	@Override
	public WarmspotData initialize(WarmspotData warmspotData) {
		if (warmspotData == null) {
			return null;
		}
		Hibernate.initialize(warmspotData.getInspector());
		return warmspotData;
	}

}
