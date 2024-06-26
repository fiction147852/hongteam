package com.son.app.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.son.app.exam.service.ParentExamService;
@Controller
public class ParentExamController {
	@Autowired
	ParentExamService parentexamService;
	
    @GetMapping("parent/exam")
    public String getParentExam(Model model) {
        return "member/parent/parent_exam";
    }
}