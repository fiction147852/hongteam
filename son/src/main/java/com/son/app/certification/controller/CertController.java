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
	@PostMapping("/emailConfirm")
	public int emailConfirm(String mail) {
		
		int number = service.sendEmail(mail);
		
		return number;
	}
}
