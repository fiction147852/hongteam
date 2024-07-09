package com.son.app.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.son.app.member.service.ParentService;
import com.son.app.member.service.StudentVO;
import com.son.app.security.service.CustomUserDetails;

@Controller
public class ParentController {
	
	@Autowired
	ParentService parentService;

    @GetMapping("parent")
    public String getParent(@AuthenticationPrincipal CustomUserDetails principal, HttpServletRequest request) {
    	int parentNumber = principal.getMember().getIdNumber();
    	
		List<StudentVO> list = parentService.ParentInfoList(parentNumber);
		HttpSession session = request.getSession();
		
		session.setAttribute("parent", list);
		
        return "member/parent/parent_info";
    }
    
    @GetMapping("parent/{studentNumber}")
    public String childInfo() {
    	
    	return "member/parent/child_info";
    }
}
	
