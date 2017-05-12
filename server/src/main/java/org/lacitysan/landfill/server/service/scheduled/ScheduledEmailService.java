package org.lacitysan.landfill.server.service.scheduled;

import org.lacitysan.landfill.server.persistence.dao.scheduled.ScheduledEmailDao;
import org.lacitysan.landfill.server.persistence.entity.email.EmailRecipient;
import org.lacitysan.landfill.server.persistence.entity.report.ScheduledReportQuery;
import org.lacitysan.landfill.server.persistence.entity.scheduled.ScheduledEmail;
import org.lacitysan.landfill.server.persistence.entity.scheduled.ScheduledReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alvin Quach
 */
@Service
public class ScheduledEmailService {
	
	@Autowired
	ScheduledEmailDao scheduledEmailDao;
	
	public ScheduledEmail create(ScheduledEmail scheduledEmail) {
		if (scheduledEmail == null) {
			return null;
		}
		// TODO Validate data.
		updateReferences(scheduledEmail);
		return scheduledEmailDao.create(scheduledEmail);
	}
	
	public ScheduledEmail update(ScheduledEmail scheduledEmail) {
		if (scheduledEmail == null) {
			return null;
		}
		// TODO Validate data.
		updateReferences(scheduledEmail);
		return scheduledEmailDao.update(scheduledEmail);
	}
	
	public ScheduledEmail delete(ScheduledEmail scheduledEmail) {
		if (scheduledEmail == null) {
			return null;
		}
		return scheduledEmailDao.delete(scheduledEmail);
	}
	
	private ScheduledEmail updateReferences(ScheduledEmail scheduledEmail) {
		for (EmailRecipient emailRecipient : scheduledEmail.getRecipients()) {
			emailRecipient.setScheduledEmail(scheduledEmail);
		}
		if (scheduledEmail instanceof ScheduledReport) {
			for (ScheduledReportQuery reportQuery : ((ScheduledReport)scheduledEmail).getReportQueries()) {
				reportQuery.setScheduledReport((ScheduledReport)scheduledEmail);
			}
		}
		return scheduledEmail;
	}
	
}
