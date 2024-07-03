package com.son.app.certification.service;

import org.springframework.mail.SimpleMailMessage;

import com.son.app.member.service.StudentVO;

public interface CertService {
	public void createNumber();
	public SimpleMailMessage createMail(String mail);
	public int sendEmail(String id);
	public void sendPhone(String phone, int number);
	public int emailDoubleCheck(String mail);
	public StudentVO childMailCheck(String mail);
}
