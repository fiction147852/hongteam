package com.son.app.counsel.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.son.app.counsel.service.CounselVO;
import com.son.app.counsel.service.impl.MainCounselServiceImpl;

@Controller
public class MainCounselController {
	
	@Autowired
	MainCounselServiceImpl service;
	
	@GetMapping("/admission")
	public String admission() {
		return "main/admission";
	}
	
	@ResponseBody
	@PostMapping("/mainCounselInsert")
	public int mainCounselInsert(CounselVO cvo) {
		return service.mainCounselInsert(cvo);
	}
}
