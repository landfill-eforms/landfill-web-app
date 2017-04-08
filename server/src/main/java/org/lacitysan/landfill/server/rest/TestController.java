package org.lacitysan.landfill.server.rest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.scheduled.ScheduledEmailDao;
import org.lacitysan.landfill.server.persistence.entity.email.EmailRecipient;
import org.lacitysan.landfill.server.persistence.entity.scheduled.Schedule;
import org.lacitysan.landfill.server.persistence.entity.scheduled.ScheduledReport;
import org.lacitysan.landfill.server.persistence.enums.EmailRecipientType;
import org.lacitysan.landfill.server.persistence.enums.SchedulePeriodBoundary;
import org.lacitysan.landfill.server.persistence.enums.ScheduleRecurrence;
import org.lacitysan.landfill.server.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(ApplicationConstant.RESOURCE_PATH + "/test")
@RestController
public class TestController {

	@Autowired
	ScheduledEmailDao scheduledEmailDao;
	
	@Autowired
	EmailService emailService;
	
	@RequestMapping(value="/email", method=RequestMethod.GET)
	public void sendEmail() {
		emailService.sendHourlyTestEmail();
	}
	
	@RequestMapping(value="/hello/{freq}", method=RequestMethod.GET)
	public Object hello(@PathVariable Integer freq) {
		ScheduledReport asdf = new ScheduledReport();
		asdf.setSchedule(new Schedule());
		asdf.getSchedule().setRecurrence(ScheduleRecurrence.MINUTELY);
		asdf.getSchedule().setOffset(Timestamp.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
		asdf.getSchedule().setFrequency(freq);
		asdf.getSchedule().setActive(true);
		asdf.getSchedule().setPeriodBoundary(SchedulePeriodBoundary.UNDEFINED);
		asdf.setSubject(freq + " Minute Test Test");
		asdf.setBody("You should be receiving this email every " + freq + " minutes.\nSorry for the spam.");
		asdf.setJoo(69);
		Set<EmailRecipient> recipients = new HashSet<>();
		recipients.add(new EmailRecipient(EmailRecipientType.TO, "alvinthingy@gmail.com", "Alvin Quach"));
		recipients.add(new EmailRecipient(EmailRecipientType.CC, "aaleman11@gmail.com", "Alfredo Aleman"));
		recipients.add(new EmailRecipient(EmailRecipientType.CC, "ligerx000@gmail.com", "Allen Huang"));
		recipients.add(new EmailRecipient(EmailRecipientType.CC, "alvinquach91@gmail.com", "Alvin Quach"));
		recipients.add(new EmailRecipient(EmailRecipientType.CC, "ow.chris.t@gmail.com", "Chris Ow"));
		recipients.add(new EmailRecipient(EmailRecipientType.CC, "3s.grantkang@gmail.com", "Grant Kang"));
		for (EmailRecipient r : recipients) {
			r.setScheduledEmail(asdf);
		}
		asdf.setRecipients(recipients);
		return scheduledEmailDao.create(asdf);
	}
	
	@RequestMapping(value="/hi", method=RequestMethod.GET)
	public Object asdf() {
		return scheduledEmailDao.getAll();
	}
		
}
