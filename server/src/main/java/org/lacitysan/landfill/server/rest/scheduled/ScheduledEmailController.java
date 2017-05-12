package org.lacitysan.landfill.server.rest.scheduled;

import java.util.List;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.scheduled.ScheduledEmailDao;
import org.lacitysan.landfill.server.persistence.entity.scheduled.ScheduledEmail;
import org.lacitysan.landfill.server.persistence.entity.scheduled.ScheduledNotification;
import org.lacitysan.landfill.server.persistence.entity.scheduled.ScheduledReport;
import org.lacitysan.landfill.server.persistence.enums.user.UserPermission;
import org.lacitysan.landfill.server.security.annotation.RestControllerSecurity;
import org.lacitysan.landfill.server.security.annotation.RestSecurity;
import org.lacitysan.landfill.server.security.annotation.RestSecurityMode;
import org.lacitysan.landfill.server.service.scheduled.ScheduledEmailService;
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
	
	@Autowired
	ScheduledEmailService scheduledEmailService;

	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<ScheduledEmail> getAll() {
		return scheduledEmailDao.getAll();
	}
	
	@RestSecurity(value=UserPermission.SCHEDULE_EMAIL_REPORTS, mode=RestSecurityMode.OVERRIDE)
	@RequestMapping(value="/list/all/report", method=RequestMethod.GET)
	public List<ScheduledReport> getAllScheduledReports() {
		return scheduledEmailDao.getAllScheduledReports();
	}
	
	@RestSecurity(value=UserPermission.SCHEDULE_EMAIL_NOTIFICATIONS, mode=RestSecurityMode.OVERRIDE)
	@RequestMapping(value="/list/all/notification", method=RequestMethod.GET)
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

	@RequestMapping(value="/create/report", method=RequestMethod.POST)
	public ScheduledEmail createScheduledReport(@RequestBody ScheduledReport scheduledReport) {
		return scheduledEmailService.create(scheduledReport); // TODO Create service for this.
	}
	
	@RequestMapping(value="/create/notification", method=RequestMethod.POST)
	public ScheduledEmail createScheduledNotification(@RequestBody ScheduledNotification scheduledNotification) {
		return scheduledEmailService.create(scheduledNotification);
	}

	@RequestMapping(value="/update/report", method=RequestMethod.POST)
	public ScheduledEmail updateScheduledReport(@RequestBody ScheduledReport scheduledReport) {
		return scheduledEmailService.update(scheduledReport);
	}
	
	@RequestMapping(value="/update/notification", method=RequestMethod.POST)
	public ScheduledEmail updateScheduledNotification(@RequestBody ScheduledNotification scheduledNotification) {
		return scheduledEmailService.update(scheduledNotification);
	}

	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ScheduledEmail delete(@RequestBody ScheduledEmail scheduledEmail) {
		return scheduledEmailService.delete(scheduledEmail);
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ScheduledEmail deleteById(@PathVariable String id) {
		return scheduledEmailService.delete(getById(id));
	}

}
