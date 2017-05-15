package org.lacitysan.landfill.server.service.email;

import java.util.Collection;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.entity.email.EmailRecipient;
import org.lacitysan.landfill.server.persistence.entity.scheduled.ScheduledEmail;
import org.lacitysan.landfill.server.persistence.entity.user.UserGroup;
import org.lacitysan.landfill.server.persistence.enums.email.EmailRecipientType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Handles the sending of emails and other logical operation for emails.
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

	/**
	 * Sends a scheduled email along with attachments, if any.
	 * Will automatically generate a set of recipients from the user groups and individual recipients of the <code>ScheduledEmail</code> object.
	 * Calls the <code>sendEmail()</code> method internally.
	 * @param scheduledEmail The scheduled email to be sent.
	 * @param attachments Attachments to be sent with the email.
	 */
	public void sendScheduledEmail(ScheduledEmail scheduledEmail, Collection<byte[]> attachments) {
		sendEmail(generateRecipientSet(scheduledEmail.getUserGroups(), scheduledEmail.getRecipients()), scheduledEmail.getSubject(), scheduledEmail.getBody(), attachments);
	}

	/**
	 * Sends an email.
	 * @param recipients The set of recipients to send the email to.
	 * @param subject The subject of the email.
	 * @param body The body of the email.
	 * @param attachments Attachments to be sent with the email.
	 */
	public void sendEmail(Collection<EmailRecipient> recipients, String subject, String body, Collection<byte[]> attachments) {

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
			
			// Create new message.
			Message message = new MimeMessage(session);
			
			// Set the sender info for the message.
			message.setFrom(new InternetAddress(username, "Landfill e-Forms"));
			
			// Add recipients to the message.
			for (EmailRecipient recipient : recipients) {
				message.addRecipient(recipient.getRecipientType().getJavaxRecipientType(), new InternetAddress(recipient.getEmailAddress(), recipient.getName()));
			}
			
			// Set subject to the message.
			message.setSubject(subject);

			// If there are no attachments, then just set the text body of the message.
			if (attachments == null || attachments.isEmpty()) {
				message.setText(body);
			}
			
			// If there are attachments, then add them to the message.
			else {
				
				// Create new multipart.
				Multipart multipart = new MimeMultipart();
				
				// Create a new body part for the message.
				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setText(body);
				multipart.addBodyPart(messageBodyPart);
				
				// Add attachments
				for (byte[] attachment : attachments) {
					messageBodyPart = new MimeBodyPart();
					ByteArrayDataSource dataSource = new ByteArrayDataSource(attachment, "application/pdf"); 
					messageBodyPart.setDataHandler(new DataHandler(dataSource));
					messageBodyPart.setFileName("report.pdf"); // TODO Generate more informative filename.
					multipart.addBodyPart(messageBodyPart);
				}
				
				// Set the contents of the email to the multipart.
				message.setContent(multipart);
			}
			
			// Send the message.
			Transport.send(message);
			if (ApplicationConstant.DEBUG) System.out.println("DEBUG:\tEmail Sent.");
		}
		catch (Exception e) {
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
	 * @param a The first email address to be compared.
	 * @param b The second email address to be compared.
	 */
	public boolean compareEmailAddresses(String a, String b) {
		return stripEmailAddress(a).equals(stripEmailAddress(b));
	}

	/**
	 * Simplifies the input email address.
	 * Gets rid of uninterpreted characters.
	 * @param emailAddress
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
