package com.spring.MailController;



import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;



@Controller
public class MailController {
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	public String SendMail(String to, String subject, String content) throws MessagingException {
		// dto.setM_userEmailHash(SHA256.getSHA256(dto.getM_userEmail()));
		String userName="ekekekem159";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
		
	
		return null;

	}

}
