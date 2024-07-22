	package com.son.app.question.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.son.app.lecture.service.InsLectureService;
import com.son.app.lecture.service.LectureVO;
import com.son.app.question.service.QuestionService;
import com.son.app.question.service.QuestionVO;
import com.son.app.security.service.CustomUserDetails;

@Controller
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	InsLectureService lectureService;
	// 전체
	@GetMapping("/instructor/questionList")
	public String questionList(@AuthenticationPrincipal CustomUserDetails userDetails,
							   @RequestParam Map<String, String> params,
            				   Model model) {
		int instructorNumber = userDetails.getMember().getIdNumber();
		 
		List<QuestionVO> searchResults = questionService.searchQuestions(params);
		
		List<LectureVO> lectureList = lectureService.getLecturesByInstructor(instructorNumber);
		model.addAttribute("lectureList", lectureList);
		
	    model.addAttribute("questionList", searchResults);
		return "question/instructor/qlist";
	}
	
	// 단건
	@GetMapping("/instructor/questionInfo")
	public String questionInfo(@AuthenticationPrincipal CustomUserDetails userDetails, QuestionVO questionVO, Model model) {
		QuestionVO findVO = questionService.questionInfo(questionVO);
		
		int instructorNumber = userDetails.getMember().getIdNumber();
		List<LectureVO> lectureList = lectureService.getLecturesByInstructor(instructorNumber);
		model.addAttribute("lectureList", lectureList);
		
		model.addAttribute("questionInfo", findVO);
		return "question/instructor/qinfo";
	}
	
	// 등록 페이지
	@GetMapping("/instructor/questionInsert")
	public String questionInsertForm(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		int instructorNumber = userDetails.getMember().getIdNumber();
		List<LectureVO> lectureList = lectureService.getLecturesByInstructor(instructorNumber);
		model.addAttribute("lectureList", lectureList);
		
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
	public String questionUpdateForm(@AuthenticationPrincipal CustomUserDetails userDetails, QuestionVO questionVO, Model model) {
		int instructorNumber = userDetails.getMember().getIdNumber();
		List<LectureVO> lectureList = lectureService.getLecturesByInstructor(instructorNumber);
		model.addAttribute("lectureList", lectureList);
		
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
		       Map.of("code", "A001", "name", "국어"),
		       Map.of("code", "A002", "name", "영어"),
		       Map.of("code", "A003", "name", "수학")
		    );
	    model.addAttribute("subjects", subjects);
	    
	    // 세부 과목 목록 가져오기
	    List<Map<String, String>> detailSubjects = List.of(
			       Map.of("code", "B001", "name", "문학"),
			       Map.of("code", "B002", "name", "화법과 작문"),
			       Map.of("code", "B003", "name", "언어와 매체"),
			       Map.of("code", "B004", "name", "독해"),
			       Map.of("code", "B005", "name", "문법"),
			       Map.of("code", "B006", "name", "미적분"),
			       Map.of("code", "B007", "name", "확률과 통계"),
			       Map.of("code", "B008", "name", "기하와 벡터")
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
