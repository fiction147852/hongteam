package com.son.app.lecture.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.son.app.lecture.service.InsLectureService;
import com.son.app.lecture.service.LectureVO;
import com.son.app.security.service.CustomUserDetails;

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
	
	@GetMapping("/instructor/{lectureNumber}")
	public String detailLecturePage(@AuthenticationPrincipal CustomUserDetails principal, @PathVariable Integer lectureNumber, HttpSession httpSession, Model model) {
		int instructorNumber = principal.getMember().getIdNumber();
		
	    LectureVO lectureInfo = lectureService.lectureInfo(lectureNumber);
	    httpSession.setAttribute("instructorLectureInfo", lectureInfo);

	    List<Integer> studentNumbers = lectureService.getStudentNumbersByLecture(lectureNumber);
	    model.addAttribute("studentNumbers", studentNumbers);

	    return "attendance/instructor/detaillecs";
	}
}
