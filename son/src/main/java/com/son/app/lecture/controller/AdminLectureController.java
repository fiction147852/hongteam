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
import com.son.app.lecture.service.Criteria;
import com.son.app.lecture.service.LectureStudentVO;
import com.son.app.lecture.service.LectureSubjectDetailVO;
import com.son.app.lecture.service.LectureSubjectVO;
import com.son.app.lecture.service.LectureVO;
import com.son.app.lecture.service.PageDTO;
import com.son.app.lecture.service.RegistrationVO;
import com.son.app.member.service.InstructorVO;

@Controller
@lombok.extern.slf4j.Slf4j
public class AdminLectureController {
	
	@Autowired
	AdminLectureService adminLectureService;
	
	//강의 리스트 및 세부사항 리스트 조회
	@GetMapping("admin/adminlectureList")
	public String adminLectureList(Model model, Criteria cri) {
		List<LectureSubjectVO> lecSublist = adminLectureService.adminLectureSubjectList();
		model.addAttribute("lecSublist", lecSublist);
		
		List<LectureSubjectDetailVO> lecSubDeList = adminLectureService.adminLectureSubjectDetailList();
		model.addAttribute("lecSubDeList", lecSubDeList);
		
		List<LectureVO> lecList = adminLectureService.adminLectureList(cri);
		model.addAttribute("adminLecList", lecList);

		List<InstructorVO> instructorVO = adminLectureService.adminInstructorList();
		model.addAttribute("adminInstruList", instructorVO);

		int total = adminLectureService.lecPageing(cri);
		model.addAttribute("page", new PageDTO(cri, total));
		
		return "lecture/admin/adminLectureList";
	}
	
	// 강의별 세부정보
	@ResponseBody
	@GetMapping("admin/adminLectureInfo")
	public LectureVO adminLectureInfo(Integer lectureNumber) {
		LectureVO adminLecInfo = adminLectureService.adminLectureInfo(lectureNumber);
		System.out.println("sd" + adminLecInfo);
		return adminLecInfo;
	}
	
	//강의 등록하기
	@PostMapping("admin/adminLectureInsert")
	@ResponseBody
	public LectureVO adminLectureInsert(@RequestBody LectureVO lectureVO) {
		log.info("BEFORE : " + lectureVO.toString());
		int result = adminLectureService.adminLectureInsert(lectureVO);
		log.info("AFTER : " + lectureVO.toString());
		return lectureVO;
	}
	
//	@GetMapping("admin/adminLectureSubjectList")
//	public LectureSubjectVO adminLectureSubjectList(Model model) {
//		LectureSubjectVO lecSublist = adminLectureService.adminLectureSubjectList();
//		System.out.println("ㅇㅇ" + lecSublist);
//		return lecSublist;
//	}
	
	//강의 별 학생 리스트
	@ResponseBody
	@GetMapping("admin/adminLectureStudentList")
	public List<LectureStudentVO> adminLectureStudentList(Integer lectureNumber) {
		List<LectureStudentVO> lecStuList = adminLectureService.adminLectureStudInfo(lectureNumber);
		return lecStuList;
	}
	
	// 이메일 검색해서 학생 찾기
	@ResponseBody
	@GetMapping("admin/adminLectureStudentSearch")
	public LectureStudentVO adminLectureStudentSearch(String email) {
		LectureStudentVO stuName = adminLectureService.adminLectureStudEmail(email);
		System.out.println(stuName + "이름");
		return stuName;
	}
	
	//학생 추가하기
	@ResponseBody
	@PostMapping("admin/adminLectureStudentInsert")
	public int adminLectureStudentInsert(@RequestBody RegistrationVO registrationVO){
		adminLectureService.adminLectureStudNumInsert(registrationVO);
		
		return 1;
	}
	
	
}
