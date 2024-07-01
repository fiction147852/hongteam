package com.son.app.certification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.son.app.certification.service.MailVO;
import com.son.app.certification.service.impl.CertServiceImpl;

@Controller
public class CertController {
	@Autowired
	CertServiceImpl service;
	
	@ResponseBody
	@PostMapping("/sec/emailConfirm")
	public int emailConfirm(String mail) {
		
		int number = service.sendEmail(mail);
		
		return number;
	}
	
	@ResponseBody
	@PostMapping("/sec/phoneConfirm")
	public int phoneConfirm(String phone) {
		int number = (int)((Math.random()*(9999 - 1000 + 1)) + 1000);
		
		service.sendPhone(phone, number);
		
		return number;
	}
	
	@ResponseBody
	@PostMapping("/sec/emailDoubleCheck")
	public int emailDoubleCheck(String mail) {
		int cnt = service.emailDoubleCheck(mail);
		
		return cnt;
	}
}
