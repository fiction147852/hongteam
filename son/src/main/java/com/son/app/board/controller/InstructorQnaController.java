package com.son.app.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.son.app.board.service.ParentCounselVO;
import com.son.app.board.service.impl.InstructorQnaServiceImpl;
import com.son.app.page.PageVO;

@Controller
public class InstructorQnaController {
	
	@Autowired
	InstructorQnaServiceImpl service;
	
	@GetMapping("/instructor/{lectureNumber}/qnaList")
	public String qnaList(@PathVariable Integer lectureNumber,
	                       @RequestParam(defaultValue = "1") int page,
	                       Model model) {
	    PageVO pageVO;
	    List<ParentCounselVO> qnaList;
	    
        pageVO = service.getPageInfo(lectureNumber, page);
        qnaList = service.qnaList(lectureNumber, pageVO);

	    model.addAttribute("qnaList", qnaList);
	    model.addAttribute("pageVO", pageVO);
	    model.addAttribute("lectureNumber", lectureNumber);
	    
	    return "board/instructor/qnaList";
	}
	
	@GetMapping("/instructor/{lectureNumber}/qnaInfo")
	public String qnaInfo(@PathVariable Integer lectureNumber, Integer counselNumber, Model model) {
		
		ParentCounselVO qvo = service.qnaInfo(counselNumber);
		
		model.addAttribute("question", qvo);
	    model.addAttribute("lectureNumber", lectureNumber);
		
		return "board/instructor/qnaInfo";
	}
	
//	@ResponseBody
//	@PostMapping("/instructor/")
}
