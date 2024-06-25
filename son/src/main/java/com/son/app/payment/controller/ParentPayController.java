package com.son.app.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.son.app.payment.service.ParentPayService;
@Controller
public class ParentPayController {
	@Autowired
	ParentPayService parentpayService;
	
    @GetMapping("parent/pay")
    public String getParentPay(Model model) {
        return "member/parent/parent_pay";
    }
}

