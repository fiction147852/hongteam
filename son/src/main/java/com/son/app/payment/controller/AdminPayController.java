package com.son.app.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.son.app.lecture.service.RegistrationVO;
import com.son.app.payment.service.AdminPayService;
import com.son.app.payment.service.AdminPayVO;
@Controller
public class AdminPayController {
	@Autowired
	AdminPayService adminPayService;
	
	// 납부 현황 리스트 조회
	@GetMapping("admin/adminPaymentCheck")
	public String adminLecturePayList(Model model){
		List<AdminPayVO> payList = adminPayService.lecturePayList();
		System.out.println("Pay List: " + payList); 
		model.addAttribute("payList", payList);
		return "payment/adminPaymentCheck";
	}
	
	// 결제 후 학생 등록 버튼  
	@ResponseBody
	@PostMapping("admin/adminPayInsertStudent")
	public int adminPayInsertStudent(@RequestBody RegistrationVO registrationVO) {
		adminPayService.adminPayInsertStudent(registrationVO);
		return 1;
	}
}

