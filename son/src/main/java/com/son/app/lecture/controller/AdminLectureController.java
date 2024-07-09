package com.son.app.lecture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.son.app.lecture.service.AdminLectureService;
import com.son.app.lecture.service.LectureVO;

@Controller
public class AdminLectureController {
	
	@Autowired
	AdminLectureService adminlectureService;
	
	@GetMapping("admin/adminlectureList")
	public String adminLectureList(Model model) {
		List<LectureVO> list = adminlectureService.adminLectureList();
		model.addAttribute("adminLecList", list);
		
		return "lecture/admin/adminLectureList";
	}
	
	@ResponseBody
	@GetMapping("admin/adminLectureInfo")
	public LectureVO adminLectureInfo(Integer lectureNumber) {
		LectureVO adminLecInfo = adminlectureService.adminLectureInfo(lectureNumber);
		System.out.println("sd" + adminLecInfo);
		return adminLecInfo;
	}
}
