package com.son.app.counsel.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainCounselController {
	
	@GetMapping("/")
	public String mainPage() {
		return "main/index";
	}
	
	@GetMapping("/admission")
	public String admission() {
		return "main/admission";
	}
	
	@GetMapping("/lectures")
	public String lectures() {
		return "main/lectures";
	}
}
