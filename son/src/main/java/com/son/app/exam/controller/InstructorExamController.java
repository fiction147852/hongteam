package com.son.app.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.son.app.exam.service.ExamVO;
import com.son.app.exam.service.InstructorExamService;
import com.son.app.lecture.service.InsLectureService;
import com.son.app.lecture.service.LectureVO;
import com.son.app.page.PageVO;
import com.son.app.paper.service.PaperService;
import com.son.app.paper.service.PaperVO;

@Controller
public class InstructorExamController {
	
	@Autowired
	InstructorExamService instructorExamService;
	
	@Autowired
	PaperService paperService;
	
	@Autowired
	InsLectureService lectureService;
	
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
	
	@GetMapping("instructor/createExam")
	public String showCreateExamForm(@RequestParam Integer paperNumber, Model model) {
	    // paperNumber를 이용해 시험지 정보를 가져옵니다
	    PaperVO paper = paperService.getPaperByNumber(paperNumber);
	    model.addAttribute("paper", paper);
	    
	    // 강의 목록을 가져와 모델에 추가합니다
	    List<LectureVO> lectures = lectureService.lectureList();
	    model.addAttribute("lectures", lectures);
	    
	    return "exam/instructor/createExamForm";
	}
	
	@PostMapping("instructor/createExam")
	public String createExam(@ModelAttribute ExamVO exam) {
	    instructorExamService.createExam(exam);
	    return "redirect:/instructor/" + exam.getLectureNumber() + "/examList";
	}
	
}
