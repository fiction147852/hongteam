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
	    if (findVO == null) {
	        return "error/questionNotFound";
	    }
	    
	    model.addAttribute("questionInfo", findVO);
	    
	    // 문제 유형
	    List<Map<String, String>> questionTypes = List.of(
	        Map.of("code", "객관식", "name", "객관식"),
	        Map.of("code", "주관식", "name", "주관식")
	    );
	    model.addAttribute("questionTypes", questionTypes);
	    
	    // 난이도
	    List<Map<String, String>> difficulties = List.of(
	        Map.of("code", "상", "name", "상"),
	        Map.of("code", "중", "name", "중"),
	        Map.of("code", "하", "name", "하")
	    );
	    model.addAttribute("difficulties", difficulties);
	    
	    // 과목 목록 가져오기
	    List<Map<String, String>> subjects = List.of(
		       Map.of("code", "A001", "name", "수학"),
		       Map.of("code", "A002", "name", "영어"),
		       Map.of("code", "A003", "name", "국어")
		    );
	    model.addAttribute("subjects", subjects);
	    
	    // 세부 과목 목록 가져오기
	    List<Map<String, String>> detailSubjects = List.of(
			       Map.of("code", "B001", "name", "미적분"),
			       Map.of("code", "B002", "name", "확률과 통계"),
			       Map.of("code", "B003", "name", "독해"),
			       Map.of("code", "B004", "name", "문법"),
			       Map.of("code", "B005", "name", "화법과 작문"),
			       Map.of("code", "B006", "name", "언어와 매체")
			    );
	    model.addAttribute("detailSubjects", detailSubjects);
	    
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
