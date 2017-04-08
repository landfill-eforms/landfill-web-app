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
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<ScheduledReport> getAllScheduledReports() {
		List<ScheduledReport> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(ScheduledReport.class)
				.list();
		result.stream().map(e -> initialize(e)).filter(e -> e != null).collect(Collectors.toList());
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<ScheduledNotification> getAllScheduledNotifications() {
		List<ScheduledNotification> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(ScheduledReport.class)
				.list();
		result.stream().map(e -> initialize(e)).filter(e -> e != null).collect(Collectors.toList());
		return result;
	}

	@Override
	public ScheduledEmail initialize(Object entity) {
		if (entity instanceof ScheduledEmail) {
			ScheduledEmail scheduledEmail = (ScheduledEmail)entity;
			Hibernate.initialize(scheduledEmail.getSchedule());
			Hibernate.initialize(scheduledEmail.getRecipients());
			scheduledEmail.getUserGroups().forEach(userGroup -> Hibernate.initialize(userGroup.getUserPermissions()));
			return scheduledEmail;
		}
		return null;
	}
	
}
