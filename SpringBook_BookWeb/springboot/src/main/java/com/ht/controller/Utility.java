package com.ht.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import org.springframework.mail.*;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

//import org.springframework.mail.javamail.MimeMessage;
//import org.springframework.mail.javamail.MimeMessageHelper;

public class Utility {
	public static String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}
//	

//	public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
//		MimeMessage message = mailSender.createMimeMessage();
//		MimeMailMessage m = MailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(message);
//
//		helper.setFrom("contact@shopme.com", "Shopme Support");
//		helper.setTo(recipientEmail);
//
//		String subject = "Here's the link to reset your password";
//
//		String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
//				+ "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + link
//				+ "\">Change my password</a></p>" + "<br>" + "<p>Ignore this email if you do remember your password, "
//				+ "or you have not made the request.</p>";
//
//		helper.setSubject(subject);
//
//		helper.setText(content, true);
//
//		mailSender.send(message);
//	}
}