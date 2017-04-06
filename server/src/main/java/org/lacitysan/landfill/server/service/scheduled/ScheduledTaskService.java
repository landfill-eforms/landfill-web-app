package org.lacitysan.landfill.server.service.scheduled;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.mail.Message;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.scheduled.ScheduledReportDao;
import org.lacitysan.landfill.server.persistence.entity.email.EmailRecipient;
import org.lacitysan.landfill.server.persistence.entity.user.UserGroup;
import org.lacitysan.landfill.server.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTaskService {

	@Autowired
	ScheduledReportDao scheduledReportDao;
	
	@Autowired
	EmailService emailService;
	
	public void runScheduledTasks() {
		Calendar now = new GregorianCalendar();
		now.setTimeZone(TimeZone.getTimeZone("PST"));
		if (ApplicationConstant.DEBUG) System.out.println("DEBUG:\t" + now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));
		if (now.get(Calendar.MINUTE) == 0) {
			emailService.sendHourlyTestEmail();
		}
		if (now.get(Calendar.HOUR_OF_DAY) < 6 && now.get(Calendar.MINUTE) % 15 == 0) {
			List<EmailRecipient> recipients = new ArrayList<>();
			recipients.add(new EmailRecipient(Message.RecipientType.TO, "alvinthingy@gmail.com", "Alvin Quach"));
			recipients.add(new EmailRecipient(Message.RecipientType.CC, "aaleman11@gmail.com", "Alfredo Aleman"));
			recipients.add(new EmailRecipient(Message.RecipientType.CC, "ligerx000@gmail.com", "Allen Huang"));
			recipients.add(new EmailRecipient(Message.RecipientType.CC, "alvinquach91@gmail.com", "Alvin Quach"));
			recipients.add(new EmailRecipient(Message.RecipientType.CC, "ow.chris.t@gmail.com", "Chris Ow"));
			recipients.add(new EmailRecipient(Message.RecipientType.CC, "3s.grantkang@gmail.com", "Grant Kang"));
			emailService.sendEmail(recipients, "15 Minute Email Test", "This is a test. You should be receiving this email every 15 minutes before 6:00 AM. Sorry for the spam.\n" + now.getTimeInMillis());
		}
	}
	
//	public

	public Set<EmailRecipient> generateRecipientSet(Collection<UserGroup> userGroups, Collection<EmailRecipient> recipients) {
		Set<EmailRecipient> result = new TreeSet<>();
		result.addAll(userGroups.stream()
				.flatMap(userGroup -> userGroup.getUsers().stream())
				.map(user -> new EmailRecipient(Message.RecipientType.TO, user.getEmailAddress(), user.getFirstname() + " " + user.getLastname()))
				.collect(Collectors.toList()));
		for (EmailRecipient recipient : recipients) {
			boolean exists = false;
			for (EmailRecipient existing : result) {
				if (emailService.compareEmailAddresses(recipient.getEmailAddress(), existing.getEmailAddress())) {
					existing.setType(recipient.getType());
					existing.setName(recipient.getName());
					existing.setEmailAddress(recipient.getEmailAddress());
					exists = true;
					break;
				}
			}
			if (!exists) {
				result.add(recipient);
			}
		}
		return result;
	}

}