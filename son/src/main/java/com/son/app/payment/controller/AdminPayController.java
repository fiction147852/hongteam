package com.son.app.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.son.app.payment.service.AdminPayService;
@Controller
public class AdminPayController {
	@Autowired
	AdminPayService adminPayService;

	
}

