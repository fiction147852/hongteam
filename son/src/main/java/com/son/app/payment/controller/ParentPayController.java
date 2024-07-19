package com.son.app.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.son.app.payment.service.ParentPayVO;
import com.son.app.payment.service.impl.ParentPayServiceImpl;
import com.son.app.security.service.CustomUserDetails;
@Controller
public class ParentPayController {
	@Autowired
	ParentPayServiceImpl parentpayService;

	// 결제할 강의 리스트 
    @GetMapping("parent/pay")
    public String lecturePayList(@AuthenticationPrincipal CustomUserDetails principal, Model model) {
    	Integer parentNumber = principal.getMember().getIdNumber();
		List<ParentPayVO> list = parentpayService.LecturePayList(parentNumber);
		model.addAttribute("payment", list);
        return "member/parent/parent_pay";
    }
    
    @ResponseBody
    @PostMapping("/parent/pay/callback")
    public int paymentCallback(Integer chargeNumber) {
    	
        return parentpayService.updatePaymentStatus(chargeNumber);
    }
    
}

