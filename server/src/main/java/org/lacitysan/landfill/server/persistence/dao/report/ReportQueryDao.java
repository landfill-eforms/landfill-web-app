package org.lacitysan.landfill.server.persistence.dao.report;

import java.util.List;

import org.lacitysan.landfill.server.persistence.dao.AbstractDao;
import org.lacitysan.landfill.server.persistence.entity.report.IndividualReportQuery;
import org.lacitysan.landfill.server.persistence.entity.report.ReportQuery;
import org.lacitysan.landfill.server.persistence.entity.report.ScheduledReportQuery;

/**
 * Data access object for <code>ReportQuery</code> entities.
 * @author Alvin Quach
 */
public interface ReportQueryDao extends AbstractDao<ReportQuery> {
	
	List<ScheduledReportQuery> getAllScheduledReportQueries();
	
	List<IndividualReportQuery> getAllIndividualReportQueries();

}
