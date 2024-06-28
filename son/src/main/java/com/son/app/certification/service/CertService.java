package com.son.app.certification.service;

import org.springframework.mail.SimpleMailMessage;

public interface CertService {
	public void createNumber();
	public SimpleMailMessage createMail(String mail);
	public int sendEmail(String id);
}
