package com.son.app.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.son.app.exam.service.ExamVO;
import com.son.app.exam.service.InstructorExamService;
import com.son.app.page.PageVO;

@Controller
public class InstructorExamController {
	
	@Autowired
	InstructorExamService instructorExamService;
	
	@GetMapping("instructor/{lectureNumber}/examList")
	public String examList(@PathVariable Integer lectureNumber,
						   @RequestParam(defaultValue = "1") int page,
						   Model model) {
		PageVO pageVO = instructorExamService.getPageInfo(lectureNumber, page);
		List<ExamVO> examList = instructorExamService.examList(lectureNumber, pageVO);
		
		model.addAttribute("examList", examList);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("lectureNumber", lectureNumber);
		return "exam/instructor/insExamList";
		
	}
	
}
