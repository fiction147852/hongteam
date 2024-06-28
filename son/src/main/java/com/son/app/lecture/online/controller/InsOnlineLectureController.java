package com.son.app.lecture.online.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.son.app.lecture.online.service.InsOnlineLectureService;
import com.son.app.lecture.online.service.OnlineLectureVO;

@Controller
public class InsOnlineLectureController {
	
	@Autowired
	InsOnlineLectureService onlineLectureService;
	
	@GetMapping("/instructor/onlineLTList")
	public String onlineLectureList(Model model) {
		List<OnlineLectureVO> list = onlineLectureService.onlineLectureList();
		model.addAttribute("onlineLectureList", list);
		return "lecture/instructor/online/onltlist";
	}
	
	@GetMapping("/instructor/onlineLTInfo")
	public String onlineLectureInfo(OnlineLectureVO onlineLectureVO, Model model) {
		OnlineLectureVO findVO = onlineLectureService.onlineLecsInfo(onlineLectureVO);
		model.addAttribute("onLTinfo", findVO);
		return "lecture/instructor/online/onltinfo";
	}
	
}
