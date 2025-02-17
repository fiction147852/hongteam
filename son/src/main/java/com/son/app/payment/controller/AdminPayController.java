package com.son.app.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.son.app.lecture.service.Criteria;
import com.son.app.lecture.service.PageDTO;
import com.son.app.lecture.service.RegistrationVO;
import com.son.app.payment.service.AdminPayService;
import com.son.app.payment.service.AdminPayVO;
@Controller
public class AdminPayController {
	@Autowired
	AdminPayService adminPayService;
	
	// 납부 현황 리스트 조회
	@GetMapping("admin/adminPaymentCheck")
	public String adminLecturePayList(Model model, 
										@ModelAttribute("cri") Criteria cri){
		
		//페이징 최소값 1
		if(cri.getPageNum() == null) {
			cri.setPageNum(1);
		}
		
		List<AdminPayVO> payList = adminPayService.lecturePayList(cri);
		System.out.println("Pay List: " + payList); 
		model.addAttribute("payList", payList);
		
		int total = adminPayService.adminPayPageing(cri);
		model.addAttribute("page", new PageDTO(cri, total));
		
		
		System.out.println();
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

