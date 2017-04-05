package org.lacitysan.landfill.server.service.scheduler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.mail.Message;

import org.lacitysan.landfill.server.service.email.EmailService;
import org.lacitysan.landfill.server.service.email.model.EmailRecipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class SchedulerService {
	
	@Autowired
	EmailService emailService;
	
	@Scheduled(fixedRate=60000)
	public void test() {
//		System.out.println(Calendar.getInstance().getTimeInMillis());
		Calendar now = new GregorianCalendar();
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
	
    @Bean
    public TaskScheduler poolScheduler() {
        return new ThreadPoolTaskScheduler();
    }

}
