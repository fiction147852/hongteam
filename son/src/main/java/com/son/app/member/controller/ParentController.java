package com.son.app.member.controller;

import java.util.List;

import com.son.app.security.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.son.app.member.service.ParentService;
import com.son.app.member.service.StudentVO;
import com.son.app.security.service.CustomUserDetails;

@Controller
public class ParentController {
	
	@Autowired
	ParentService parentService;
  
	@GetMapping("dsa")
    public String getParentDashboard(Model model, @AuthenticationPrincipal CustomUserDetails principal) {
		String email = principal.getMember().getEmail();
		int parentNumber = parentService.getParentNumberByEmail(email);

		List<StudentVO> studentList = parentService.getStudentsByParentNumber(parentNumber);
		model.addAttribute("studentList", studentList);
		return "common/fragments/parent_sidebar";
	}
    

    @GetMapping("parent")
    public String getParent(Model model) {
		List<StudentVO> list = parentService.ParentInfoList();
		model.addAttribute("parent", list);
		
        return "member/parent/parent_info";
    }
}
	
