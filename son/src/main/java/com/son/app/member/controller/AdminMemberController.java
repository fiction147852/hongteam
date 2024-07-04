package com.son.app.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.son.app.member.service.AdminMemberService;
import com.son.app.security.service.MemberVO;

@Controller
public class AdminMemberController {
	
	@Autowired
	AdminMemberService adminMemberService;
	
	@GetMapping("admin/adminMemberList")
	public String accountList (Model model){
		List<MemberVO> list = adminMemberService.memberList();
		model.addAttribute("memberList", list);
		
		return "counsel/adminMemberList";
	}
  
}
	
