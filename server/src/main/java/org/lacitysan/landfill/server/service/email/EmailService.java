package org.lacitysan.landfill.server.service.email;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Properties;

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
				message.addRecipient(recipient.getType(), new InternetAddress(recipient.getEmailAddress(), recipient.getName()));
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


}
