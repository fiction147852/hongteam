package com.son.app.lecture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.son.app.lecture.service.ParentLectureService;

@Controller
public class ParentLectureController {
	
	@Autowired
	ParentLectureService parentlectureService;
	
    @GetMapping("parent/lecture")
    public String getParentLecture(Model model) {
    	model.addAttribute("lectureInstructorList", parentlectureService.ParentLetureInfoList());
        return "member/parent/parent_lecture";
    }
}
