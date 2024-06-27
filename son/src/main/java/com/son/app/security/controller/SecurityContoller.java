package com.son.app.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityContoller {
	
	@GetMapping("/login")
	public String login() {
		return "security/login";
	}
	
	@GetMapping("/join")
	public String join() {
		return "security/join";
	}
	
	@GetMapping("/loginTest")
	public String loginTest() {
		return "security/test";
	}
	
//	@PostMapping("/lms/loginProcessing")
//	public 
}
