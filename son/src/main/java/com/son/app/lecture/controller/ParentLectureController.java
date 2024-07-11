package com.son.app.lecture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.son.app.lecture.service.ChildLectureVO;
import com.son.app.lecture.service.ParentLectureService;
import com.son.app.security.service.CustomUserDetails;

@Controller
public class ParentLectureController {
	
	@Autowired
	ParentLectureService parentlectureService;
	
    @GetMapping("parent/lecture")
    public String getParentLecture(Model model) {
    	model.addAttribute("lectureInstructorList", parentlectureService.ParentLectureInfoList());
        return "member/parent/parent_lecture";
    }
    
	/*
	 * @PostMapping("parent/{studentNumber}") public String
	 * childLectureList(@AuthenticationPrincipal CustomUserDetails principal, Model
	 * model) { int parentNumber = principal.getMember().getIdNumber();
	 * List<ChildLectureVO> lecture =
	 * parentlectureService.LectureList(studentNumber); return ""; }
	 */
}