package org.lacitysan.landfill.server.service.email;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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

	public void sendTestEmail() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", auth);
		props.put("mail.smtp.starttls.enable", startTlsEnable);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
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
			msg.setSubject("Testing123");
			msg.setText("This is a test. Sorry for the spam.\n" + Calendar.getInstance().getTimeInMillis());
			Transport.send(msg);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}


}
