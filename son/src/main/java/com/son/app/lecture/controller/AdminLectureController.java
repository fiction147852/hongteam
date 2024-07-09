package com.son.app.lecture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.son.app.lecture.service.AdminLectureService;
import com.son.app.lecture.service.LectureStudentVO;
import com.son.app.lecture.service.LectureSubjectDetailVO;
import com.son.app.lecture.service.LectureSubjectVO;
import com.son.app.lecture.service.LectureVO;

@Controller
public class AdminLectureController {
	
	@Autowired
	AdminLectureService adminLectureService;
	
	@GetMapping("admin/adminlectureList")
	public String adminLectureList(Model model) {
		List<LectureVO> lecList = adminLectureService.adminLectureList();
		model.addAttribute("adminLecList", lecList);
		
		List<LectureSubjectVO> lecSublist = adminLectureService.adminLectureSubjectList();
		model.addAttribute("lecSublist", lecSublist);
		
		List<LectureSubjectDetailVO> lecSubDeList = adminLectureService.adminLectureSubjectDetailList();
		model.addAttribute("lecSubDeList", lecSubDeList);
		
		return "lecture/admin/adminLectureList";
	}
	
	@ResponseBody
	@GetMapping("admin/adminLectureInfo")
	public LectureVO adminLectureInfo(Integer lectureNumber) {
		LectureVO adminLecInfo = adminLectureService.adminLectureInfo(lectureNumber);
		System.out.println("sd" + adminLecInfo);
		return adminLecInfo;
	}
	
	@ResponseBody
	@PostMapping("admin/adminLectureInsert")
	public int adminLectureInsert(@RequestBody LectureVO lectureVO) {
		adminLectureService.adminLectureInsert(lectureVO);
		
		return 1;
	}
	
//	@GetMapping("admin/adminLectureSubjectList")
//	public LectureSubjectVO adminLectureSubjectList(Model model) {
//		LectureSubjectVO lecSublist = adminLectureService.adminLectureSubjectList();
//		System.out.println("ㅇㅇ" + lecSublist);
//		return lecSublist;
//	}
	
	@ResponseBody
	@GetMapping("admin/adminLectureStudentList")
	public List<LectureStudentVO> adminLectureStudentList(Integer lectureNumber) {
		List<LectureStudentVO> lecStuList = adminLectureService.adminLectureStudInfo(lectureNumber);
		return lecStuList;
	}
	
}
