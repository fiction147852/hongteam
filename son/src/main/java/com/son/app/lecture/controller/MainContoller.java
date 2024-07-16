package com.son.app.lecture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.son.app.lecture.service.MainLectureVO;
import com.son.app.lecture.service.impl.MainLectureServiceImpl;

@Controller
public class MainContoller {
	
	@Autowired
	MainLectureServiceImpl service;
	
	@GetMapping("/")
	public String mainPage(Model model) {
		List<MainLectureVO> list = service.selectAll();
		
		model.addAttribute("list", list);
		
		return "main/lectures";
	}
	
	@GetMapping("/lectures")
	public String lectures(Model model) {
		List<MainLectureVO> list = service.selectAll();
		
		model.addAttribute("list", list);
		
		return "main/lectures";
	}
	
}
