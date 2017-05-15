package org.lacitysan.landfill.server.persistence.dao.report;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.report.IndividualReportQuery;
import org.lacitysan.landfill.server.persistence.entity.report.ReportQuery;
import org.lacitysan.landfill.server.persistence.entity.report.ScheduledReportQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implemented data access object for <code>ReportQuery</code> entities.
 * @author Alvin Quach
 */
@Repository
public class ReportQueryDaoImpl extends AbstractDaoImpl<ReportQuery> implements ReportQueryDao {

	@Override
	@Transactional
	public List<ScheduledReportQuery> getAllScheduledReportQueries() {
		List<?> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(ScheduledReportQuery.class)
				.list();
		return result.stream()
				.map(e -> {
					initialize(checkType(e)); 
					return e instanceof ScheduledReportQuery ? (ScheduledReportQuery)e : null;
				})
				.filter(e -> e != null)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<IndividualReportQuery> getAllIndividualReportQueries() {
		List<?> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(IndividualReportQuery.class)
				.list();
		return result.stream()
				.map(e -> {
					initialize(checkType(e)); 
					return e instanceof IndividualReportQuery ? (IndividualReportQuery)e : null;
				})
				.filter(e -> e != null)
				.collect(Collectors.toList());
	}

	@Override
	public ReportQuery initialize(ReportQuery reportQuery) {
		if (reportQuery == null) {
			return null;
		}
		Hibernate.initialize(reportQuery);
		return reportQuery;
	}

}
