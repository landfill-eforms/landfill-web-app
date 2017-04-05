package org.lacitysan.landfill.server.persistence.dao.scheduled;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.scheduled.ScheduledReport;
import org.springframework.stereotype.Repository;

/**
 * @author Alvin Quach
 */
@Repository
public class ScheduledReportDaoImpl extends AbstractDaoImpl<ScheduledReport> implements ScheduledReportDao {

	@Override
	public ScheduledReport initialize(Object entity) {
		if (entity instanceof ScheduledReport) {
			ScheduledReport scheduledReport = (ScheduledReport)entity;
			scheduledReport.getUserGroups().forEach(userGroup -> Hibernate.initialize(userGroup.getUserPermissions()));
			Hibernate.initialize(scheduledReport.getRecipients());
			return scheduledReport;
		}
		return null;
	}
	
}
