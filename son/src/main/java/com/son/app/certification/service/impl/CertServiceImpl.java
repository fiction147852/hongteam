package com.son.app.certification.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.son.app.certification.service.CertService;

@Service
public class CertServiceImpl implements CertService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	private String senderEmail = "thfqwl777@gmail.com";
	private int number;

	@Override
	public void createNumber() {
		number = (int) (Math.random() * (90000)) + 100000; // (int) Math.random() * (최댓값-최소값+1) + 최소값
	}

	@Override
	public SimpleMailMessage createMail(String mail) {
		createNumber();
//        MimeMessage message = javaMailSender.createMimeMessage();
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(mail);
        message.setSubject("이메일 인증");
        message.setText("인증번호 : " + number);

//        try {
//            message.setFrom(senderEmail);
//            message.setRecipients(MimeMessage.RecipientType.TO, mail);
//            message.setSubject("이메일 인증");
//            String body = "";
//            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
//            body += "<h1>" + number + "</h1>";
//            body += "<h3>" + "감사합니다." + "</h3>";
//            message.setText(body,"UTF-8", "html");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }

        return message;
	}

	@Override
	public int sendEmail(String mail) {
		
		SimpleMailMessage message = createMail(mail);

        javaMailSender.send(message);

        return number;
	}

}
