package com.spring.MailController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailHandler {
	
	 private JavaMailSender mailSender;
     private MimeMessage message;
     private MimeMessageHelper messageHelper;
     
     public MailHandler(JavaMailSender mailSender) throws MessagingException{ 
    	 this.mailSender = mailSender;
    	 message = this.mailSender.createMimeMessage();
    	 
     }

	
}
