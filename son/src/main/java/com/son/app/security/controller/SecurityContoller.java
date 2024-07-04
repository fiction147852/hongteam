package com.son.app.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.son.app.certification.service.impl.CertServiceImpl;
import com.son.app.security.service.CustomUserDetails;
import com.son.app.security.service.MemberVO;

@Controller
public class SecurityContoller {
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	CertServiceImpl service;
	
	@RequestMapping("/login")
	public String login(Model model, @RequestParam(value="error", required=false) String error) {
		if(error != null) {
			model.addAttribute("error", error);
		}
		
		return "security/login";
	}
	
	@GetMapping("/join")
	public String join() {
		return "security/join";
	}
	
	@PostMapping("/signUp")
	public String signUp(MemberVO memberInfo) {
		
		memberInfo.setPassword(passwordEncoder.encode(memberInfo.getPassword()));
		
		System.out.println(memberInfo.toString());
		
		if(memberInfo.getType().equals("2")) {
			service.parentJoin(memberInfo);
			service.setParentNo(memberInfo.getStudentNumber());
		}else {
			service.studentJoin(memberInfo);
		}
		
		return "redirect:/login";
	}
	
	@GetMapping("/loginTest")
	public String loginTest(Model model, @AuthenticationPrincipal CustomUserDetails principal, Authentication auth) {
		model.addAttribute("user", principal);
		model.addAttribute("userVO", principal.getMember());
		model.addAttribute("auth", auth.getAuthorities().toString());		
		return "security/test";
	}
	
//	@PostMapping("/lms/loginProcessing")
//	public 
}
