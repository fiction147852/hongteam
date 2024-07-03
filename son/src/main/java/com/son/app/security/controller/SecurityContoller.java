package com.son.app.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.son.app.member.service.StudentVO;
import com.son.app.security.service.CustomUserDetails;

@Controller
public class SecurityContoller {
	
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
	public String signUp(StudentVO memberInfo) {
		System.out.println(memberInfo.toString());
		return "security/join";
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
