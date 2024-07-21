package com.son.app.lecture.online.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.son.app.lecture.online.service.InsOnlineLectureService;
import com.son.app.lecture.online.service.OnlineLectureVO;
import com.son.app.page.PageVO;

@Controller
public class InsOnlineLectureController {
	
	@Autowired
	InsOnlineLectureService onlineLectureService;
	
	@GetMapping("/instructor/{lectureNumber}/onlineLTList")
	public String onlineLectureList(@PathVariable Integer lectureNumber,
									@RequestParam(defaultValue = "1") int page,
									Model model) {
		PageVO pageVO = onlineLectureService.getPageInfo(lectureNumber, page);
		List<OnlineLectureVO> list = onlineLectureService.onlineLectureList(lectureNumber, pageVO);
		
		model.addAttribute("onlineLectureList", list);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("lectureNumber", lectureNumber);
		return "lecture/instructor/online/onltlist";
	}
	
	@GetMapping("/instructor/{lectureNumber}/onlineLTInfo")
	public String onlineLectureInfo(@PathVariable Integer lectureNumber,
								 	@RequestParam Integer onlineLectureNumber, 
								 	Model model) {
		
		OnlineLectureVO findVO = onlineLectureService.onlineLecsInfo(onlineLectureNumber);
		model.addAttribute("onLTinfo", findVO);
		model.addAttribute("lectureNumber", lectureNumber);
		return "lecture/instructor/online/onltinfo";
	}
	
	// 학생
	@GetMapping("/student/{lectureNumber}/onlineLTList")
	public String onlineLectureListforStudent(@PathVariable Integer lectureNumber,
											  @RequestParam(defaultValue = "1") int page,
											  Model model) {
		PageVO pageVO = onlineLectureService.getPageInfo(lectureNumber, page);
		List<OnlineLectureVO> list = onlineLectureService.onlineLectureList(lectureNumber, pageVO);
		
		model.addAttribute("onlineLectureList", list);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("lectureNumber", lectureNumber);
		return "lecture/student/online/onltlist";
	}
	
	@GetMapping("/student/{lectureNumber}/onlineLTInfo")
	public String onlineLectureInfoforStudent(@PathVariable Integer lectureNumber,
										 	  @RequestParam Integer onlineLectureNumber, 
										 	  Model model) {
		
		OnlineLectureVO findVO = onlineLectureService.onlineLecsInfo(onlineLectureNumber);
		model.addAttribute("onLTinfo", findVO);
		model.addAttribute("lectureNumber", lectureNumber);
		return "lecture/student/online/onltinfo";
	}
	
}
