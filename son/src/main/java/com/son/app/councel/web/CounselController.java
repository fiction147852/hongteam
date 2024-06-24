package com.son.app.councel.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.son.app.councel.service.CounselService;
import com.son.app.councel.service.CounselVO;

@Controller
public class CounselController {

	@Autowired
	CounselService counselService;

	// 상담 스케줄 전체조회
	@GetMapping("counselList")
	public String counselList(Model model) {
		List<CounselVO> list = counselService.counselList();
		model.addAttribute("counsel", list);

		return "counsel/counselList";
	}

	
	// 상담 스케쥴
	@GetMapping("counselCalinder")
	public String counselCalinder(Model model) {
		List<CounselVO> list = counselService.counselCalinder();
		model.addAttribute("counselCalinder", list);
		
		return "counsel/counselCalinder";
		
	}
	
	@PostMapping("counselCalinder")
	public String counselTime(Model model) {
		List<CounselVO> list = counselService.counselTime();
		model.addAttribute("counselTime", list);
		
		return "counsel/counselCalinder";
		
	}
	
	// 상담 단건 조회
	@GetMapping("counselInfo")
	public String counselInsertForm(CounselVO counselVO, Model model) {
		CounselVO findVO = counselService.counselInfo(counselVO);
		model.addAttribute("counselInfo", findVO);
		return "counsel/counselInsert";
	}

	
	
	// 상담 등록 - 페이지
	@GetMapping("counselInsert")
	public String counsertInsert(Model model) {
		return "counsel/counselInsert";
	}
	
	// 상담 등록 - 처리 
	@PostMapping("counselInsert")
	public String counsertInsertProcess(CounselVO counselVO) {
		counselService.counselInsert(counselVO);

		return "redirect:counserList";
	}
}
