package com.son.app.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.son.app.member.service.AdminMemberService;
import com.son.app.security.service.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AdminMemberController {
	
	@Autowired
	AdminMemberService adminMemberService;
	
	@GetMapping("admin/adminMemList")
	public String accountList (Model model){
		List<MemberVO> list = adminMemberService.memberList();
		model.addAttribute("memberList", list);
		
		return "attendance/adminMemManager/adminMemList";
	}
	
	@ResponseBody
	@GetMapping("admin/adminMemInfo")
	public MemberVO accountInfo(int idNumber, String auth) {
		log.info(idNumber+"");
		log.info(auth);
		MemberVO memInfo = adminMemberService.memberInfo(idNumber, auth);  
		
		return memInfo;
	}
}
	
