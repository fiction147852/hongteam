package com.son.app.question.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.son.app.question.service.QuestionService;
import com.son.app.question.service.QuestionVO;

@Controller
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	// 전체
	@GetMapping("/instructor/questionList")
	public String questionList(Model model) {
		List<QuestionVO> list = questionService.questionList();
		model.addAttribute("questionList", list);
		return "question/instructor/questionList";
	}
}
