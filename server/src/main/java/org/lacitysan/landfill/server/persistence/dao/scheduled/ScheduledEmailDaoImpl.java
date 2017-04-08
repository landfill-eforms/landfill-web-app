package org.lacitysan.landfill.server.persistence.dao.scheduled;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.scheduled.ScheduledEmail;
import org.springframework.stereotype.Repository;

/**
 * @author Alvin Quach
 */
@Repository
public class ScheduledEmailDaoImpl extends AbstractDaoImpl<ScheduledEmail> implements ScheduledEmailDao {

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
