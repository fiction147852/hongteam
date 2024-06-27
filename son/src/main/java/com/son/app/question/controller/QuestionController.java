package com.son.app.question.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
		return "question/instructor/qlist";
	}
	
	// 단건
	@GetMapping("/instructor/questionInfo")
	public String questionInfo(QuestionVO questionVO, Model model) {
		QuestionVO findVO = questionService.questionInfo(questionVO);
		
		model.addAttribute("questionInfo", findVO);
		return "question/instructor/qinfo";
	}
	
	// 등록 페이지
	@GetMapping("/instructor/questionInsert")
	public String questionInsertForm() {
		return "question/instructor/qinsert";
	}
	
	// 등록 처리
	@PostMapping("/instructor/questionInsert")
	public String questionInsertProcess(QuestionVO questionVO) {
		questionService.insertQuestion(questionVO);
		return "redirect:/instructor/questionList";
	}
	
	// 수정 페이지
	@GetMapping("/instructor/questionUpdate")
	public String questionUpdateForm(QuestionVO questionVO, Model model) {
		QuestionVO findVO = questionService.questionInfo(questionVO);
		model.addAttribute("questionInfo", findVO);
		return "question/instructor/qupdate";
	}
	
	// 수정 처리
	@PostMapping("/instructor/questionUpdate")
	@ResponseBody
	public Map<String, Object> questUpdateJSON(@RequestBody QuestionVO questionVO) {
		return questionService.updateQuestion(questionVO);
	}
	
	// 삭제
	@GetMapping("/instructor/questionDelete")
	public String questiongDelete(Integer questionNo) {
		questionService.deleteQuestion(questionNo);
		return "redirect:/instructor/questionList";
		}
}
