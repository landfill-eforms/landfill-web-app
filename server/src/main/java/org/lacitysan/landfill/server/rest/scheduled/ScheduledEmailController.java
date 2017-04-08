package org.lacitysan.landfill.server.rest.scheduled;

import java.util.List;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.scheduled.ScheduledEmailDao;
import org.lacitysan.landfill.server.persistence.entity.scheduled.ScheduledEmail;
import org.lacitysan.landfill.server.persistence.entity.scheduled.ScheduledNotification;
import org.lacitysan.landfill.server.persistence.entity.scheduled.ScheduledReport;
import org.lacitysan.landfill.server.persistence.enums.UserPermission;
import org.lacitysan.landfill.server.security.annotation.RestControllerSecurity;
import org.lacitysan.landfill.server.security.annotation.RestSecurity;
import org.lacitysan.landfill.server.security.annotation.RestSecurityMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alvin Quach
 */
@RestControllerSecurity({UserPermission.SCHEDULE_EMAIL_REPORTS, UserPermission.SCHEDULE_EMAIL_NOTIFICATIONS})
@RequestMapping(ApplicationConstant.RESOURCE_PATH + "/schedule/email")
@RestController
public class ScheduledEmailController {

	@Autowired
	ScheduledEmailDao scheduledEmailDao;

	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<ScheduledEmail> getAll() {
		return scheduledEmailDao.getAll();
	}
	
	@RestSecurity(value=UserPermission.SCHEDULE_EMAIL_REPORTS, mode=RestSecurityMode.OVERRIDE)
	@RequestMapping(value="/list/all/reports", method=RequestMethod.GET)
	public List<ScheduledReport> getAllScheduledReports() {
		return scheduledEmailDao.getAllScheduledReports();
	}
	
	@RestSecurity(value=UserPermission.SCHEDULE_EMAIL_NOTIFICATIONS, mode=RestSecurityMode.OVERRIDE)
	@RequestMapping(value="/list/all/notifications", method=RequestMethod.GET)
	public List<ScheduledNotification> getScheduledNotifications() {
		return scheduledEmailDao.getAllScheduledNotifications();
	}

	@RequestMapping(value="/unique/id/{id}", method=RequestMethod.GET)
	public ScheduledEmail getById(@PathVariable String id) {
		try {
			return scheduledEmailDao.getById(Integer.valueOf(id));
		}
		catch (NumberFormatException e) {
			return null;
		}
	}

	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ScheduledEmail create(@RequestBody ScheduledEmail scheduledEmail) {
		if (scheduledEmail == null) return null;
		return scheduledEmailDao.create(scheduledEmail); // TODO Create service for this.
	}

	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ScheduledEmail update(@RequestBody ScheduledEmail scheduledEmail) {
		if (scheduledEmail == null) return null;
		return scheduledEmailDao.update(scheduledEmail); // TODO Create service for this.
	}

	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ScheduledEmail delete(@RequestBody ScheduledEmail scheduledEmail) {
		if (scheduledEmail == null) return null;
		return scheduledEmailDao.delete(scheduledEmail);
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ScheduledEmail deleteById(@PathVariable String id) {
		return delete(getById(id));
	}



}
