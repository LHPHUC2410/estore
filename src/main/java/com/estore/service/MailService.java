package com.estore.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.estore.bean.MailInfo;


@Service
public class MailService{

	@Autowired
	JavaMailSender javaMailSender;
	
	public void send(MailInfo mailer) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		helper.setFrom(mailer.getFrom());
		helper.setTo(mailer.getTo());
		helper.setSubject(mailer.getSubject());
		helper.setText(mailer.getBody(), true);
		helper.setReplyTo(mailer.getFrom());

		if (mailer.getCc() != null) {
			helper.setCc(mailer.getCc());
		}
		if (mailer.getBcc() != null) {
			helper.setCc(mailer.getCc());
		}
		if (mailer.getFiles() != null) {
			String[] paths = mailer.getFiles().split(";");
			for (String path : paths) {
				File file = new File(path);
				helper.addAttachment(file.getName(), file);
			}
		}
		javaMailSender.send(message);
	}
	/*
	 * public void send(String from, String to, String subject, String body) throws
	 * MessagingException { this.send(new MailInfo(to, subject, body, from)); }
	 */

}
