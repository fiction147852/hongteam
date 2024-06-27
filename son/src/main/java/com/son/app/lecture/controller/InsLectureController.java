package com.son.app.lecture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.son.app.lecture.service.InsLectureService;
import com.son.app.lecture.service.LectureVO;

@Controller
public class InsLectureController {
	
	@Autowired
	InsLectureService lectureService;
	
	@GetMapping("/instructor/lectureList")
	public String lectureList(Model model) {
		List<LectureVO> list = lectureService.lectureList();
		model.addAttribute("lectureList", list);
		return "lecture/instructor/leclist";
	}
}
