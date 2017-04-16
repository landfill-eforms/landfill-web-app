package org.lacitysan.landfill.server.service.email;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.entity.email.EmailRecipient;
import org.lacitysan.landfill.server.persistence.entity.scheduled.ScheduledEmail;
import org.lacitysan.landfill.server.persistence.entity.user.UserGroup;
import org.lacitysan.landfill.server.persistence.enums.email.EmailRecipientType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Alvin Quach
 */
@Service
public class EmailService {
	
	@Value("${mail.smtp.auth}")
	private Boolean auth;
	
	@Value("${mail.smtp.starttls.enable}")
	private Boolean startTlsEnable;
	
	@Value("${mail.smtp.host}")
	private String host;
	
	@Value("${mail.smtp.port}")
	private Integer port;
	
	@Value("${mail.smtp.username}")
	private String username;
	
	@Value("${mail.smtp.password}")
	private String password;
	
	public void sendEmail(ScheduledEmail scheduledEmail) {
		sendEmail(generateRecipientSet(scheduledEmail.getUserGroups(), scheduledEmail.getRecipients()), scheduledEmail.getSubject(), scheduledEmail.getBody());
	}
	
	public void sendEmail(Collection<EmailRecipient> recipients, String subject, String body) {
		
		if (ApplicationConstant.DEBUG) System.out.println("DEBUG:\tAttempting to Send Email.");
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", auth);
		properties.put("mail.smtp.starttls.enable", startTlsEnable);
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username, "Landfill e-Forms"));
			for (EmailRecipient recipient : recipients) {
				message.addRecipient(recipient.getRecipientType().getJavaxRecipientType(), new InternetAddress(recipient.getEmailAddress(), recipient.getName()));
			}
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
			if (ApplicationConstant.DEBUG) System.out.println("DEBUG:\tEmail Sent.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendHourlyTestEmail() {
		
		if (ApplicationConstant.DEBUG) System.out.println("DEBUG:\tAttempting to Send Email.");
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", auth);
		properties.put("mail.smtp.starttls.enable", startTlsEnable);
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("landfill.notifications@gmail.com", "Landfill e-Forms"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("alvinthingy@gmail.com", "Alvin Quach"));
			msg.addRecipient(Message.RecipientType.CC, new InternetAddress("aaleman11@gmail.com", "Alfredo Aleman"));
			msg.addRecipient(Message.RecipientType.CC, new InternetAddress("ligerx000@gmail.com", "Allen Ma"));
			msg.addRecipient(Message.RecipientType.CC, new InternetAddress("alvinquach91@gmail.com", "Alvin Quach"));
			msg.addRecipient(Message.RecipientType.CC, new InternetAddress("ow.chris.t@gmail.com", "Chris Ow"));
			msg.addRecipient(Message.RecipientType.CC, new InternetAddress("3s.grantkang@gmail.com", "Grant Kang"));
			msg.setSubject("Hourly Email Test");
			msg.setText("This is a test. You should be receiving this email every hour. Sorry for the spam.\n" + Calendar.getInstance().getTimeInMillis());
			Transport.send(msg);
			if (ApplicationConstant.DEBUG) System.out.println("DEBUG:\tEmail Sent.");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	public Set<EmailRecipient> generateRecipientSet(Collection<UserGroup> userGroups, Collection<EmailRecipient> recipients) {
		Set<EmailRecipient> result = new TreeSet<>();
		result.addAll(userGroups.stream()
				.flatMap(userGroup -> userGroup.getUsers().stream())
				.map(user -> new EmailRecipient(EmailRecipientType.TO, user.getEmailAddress(), user.getFirstname() + " " + user.getLastname()))
				.collect(Collectors.toList()));
		for (EmailRecipient recipient : recipients) {
			boolean exists = false;
			for (EmailRecipient existing : result) {
				if (compareEmailAddresses(recipient.getEmailAddress(), existing.getEmailAddress())) {
					existing.setRecipientType(recipient.getRecipientType());
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
	
	/** 
	 * Compares two email addresses. 
	 * Assumes both email addresses are valid.
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean compareEmailAddresses(String a, String b) {
		return stripEmailAddress(a).equals(stripEmailAddress(b));
	}
	
	/**
	 * Simplifies the input email address.
	 * Gets rid of uninterpreted characters.
	 * @param emailAddress
	 * @return
	 */
	private String stripEmailAddress(String emailAddress) {
		String[] split = emailAddress.toLowerCase().split("@");
		if (split.length != 2) {
			return emailAddress; // Invalid email address.
		}
		split[0] = split[0].replaceAll("\\.", "");
		int plusIndex = split[0].indexOf('+');
		if (plusIndex > -1) {
			split[0] = split[0].substring(0, plusIndex);
		}
		return split[0] + "@" + split[1];
	}

}
