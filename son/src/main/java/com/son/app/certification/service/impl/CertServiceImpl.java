package com.son.app.certification.service.impl;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.son.app.certification.mapper.CertMapper;
import com.son.app.certification.service.CertService;
import com.son.app.member.service.StudentVO;
import com.son.app.security.service.MemberVO;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
public class CertServiceImpl implements CertService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	CertMapper mapper;

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

	@Override
	public void sendPhone(String phone, int number) {
		
		System.out.println(phone + "폰폰폰폰폰폰");
		
		String api_key = "NCSFXLQ2RMRRNDC0";
		String api_secret = "5GLKCEDKYWP0TTJSN1ZOFHQVZKKX9QDD";
		Message coolsms = new Message(api_key, api_secret);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", phone); // 수신전화번호
		params.put("from", "01067681316");
		params.put("type", "SMS");
		params.put("text", "[TEST] 인증번호는" + "[" + number + "]" + "입니다.");
		params.put("app_version", "sonsonson");

		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
			System.out.println("보냈음");
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}

	}

	@Override
	public int emailDoubleCheck(String mail) {
		
		return mapper.emailDoubleCheck(mail);
	}
	
	@Override
	public StudentVO childMailCheck(String mail) {
		
		return mapper.childMailCheck(mail);
	}

	@Override
	public int studentJoin(MemberVO mvo) {
		
		return mapper.studentJoin(mvo);
	}
	
	@Override
	public int parentJoin(MemberVO mvo) {
		
		return mapper.parentJoin(mvo);
	}

	@Override
	public int setParentNo(int no) {
		
		return mapper.setParentNo(no);
	}

}
