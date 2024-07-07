package com.son.app.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.son.app.attendance.service.StudentLectureInfoVO;
import com.son.app.member.service.ParentService;
import com.son.app.member.service.StudentVO;
import com.son.app.security.service.CustomUserDetails;

@Controller
public class ParentController {
	
	@Autowired
	ParentService parentService;
  

    @GetMapping("parent")
    public String getParent(@AuthenticationPrincipal CustomUserDetails principal, Model model) {
    	int parentNumber = principal.getMember().getIdNumber();
    	
		List<StudentVO> list = parentService.ParentInfoList(parentNumber);
		model.addAttribute("parent", list);
		
        return "member/parent/parent_info";
    }
}
	
