package org.lacitysan.landfill.server.persistence.dao.scheduled;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.scheduled.ScheduledEmail;
import org.lacitysan.landfill.server.persistence.entity.scheduled.ScheduledNotification;
import org.lacitysan.landfill.server.persistence.entity.scheduled.ScheduledReport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class ScheduledEmailDaoImpl extends AbstractDaoImpl<ScheduledEmail> implements ScheduledEmailDao {

	@Override
	@Transactional
	public List<ScheduledReport> getAllScheduledReports() {
		List<?> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(ScheduledReport.class)
				.list();
		return result.stream()
				.map(e -> {
					initialize(checkType(e)); 
					return e instanceof ScheduledReport ? (ScheduledReport)e : null;
				})
				.filter(e -> e != null)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<ScheduledNotification> getAllScheduledNotifications() {
		List<?> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(ScheduledReport.class)
				.list();
		return result.stream()
				.map(e -> {
					initialize(checkType(e)); 
					return e instanceof ScheduledNotification ? (ScheduledNotification)e : null;
				})
				.filter(e -> e != null)
				.collect(Collectors.toList());
	}

	@Override
	public ScheduledEmail initialize(ScheduledEmail scheduledEmail) {
		if (scheduledEmail == null) {
			return null;
		}
		Hibernate.initialize(scheduledEmail.getSchedule());
		Hibernate.initialize(scheduledEmail.getRecipients());
		scheduledEmail.getUserGroups().forEach(userGroup -> Hibernate.initialize(userGroup.getUserPermissions()));
		if (scheduledEmail instanceof ScheduledReport) {
			// TODO Do something.
		}
		else if (scheduledEmail instanceof ScheduledNotification) {
			// TODO Do something.
		}
		return scheduledEmail;
	}


}
