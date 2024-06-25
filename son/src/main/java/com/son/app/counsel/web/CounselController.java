package com.son.app.counsel.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.son.app.counsel.service.CounselPossibilityVO;
import com.son.app.counsel.service.CounselService;
import com.son.app.counsel.service.CounselVO;

@Controller
public class CounselController {

	@Autowired
	CounselService counselService;

	@GetMapping("admin/counsel")
	public String Counsel(Model model) {

		return "counsel/counsel";
	}

	// 상담 스케줄 전체조회
	@GetMapping("admin/counselList")
	public String counselList(Model model) {
		List<CounselVO> list = counselService.counselList();
		model.addAttribute("counselList", list);

		return "counsel/counselList";
	}

	// 상담 스케쥴 보기
	@GetMapping("admin/counselCalender")
	public String counselCalinder(Model model) {
		List<CounselVO> list = counselService.counselCalender();
		model.addAttribute("counselCalender", list);

		return "counsel/counselCalender";

	}
	// 수정 - 상담 시간 조율 기능
	@PostMapping("admin/counselCalender")
	public String counselTime(String weekdaysCode, Model model) {
		CounselPossibilityVO counselPos = new CounselPossibilityVO();
		counselPos.setWeekdaysCode(weekdaysCode);
		
		List<CounselVO> list = counselService.counselTimeUpdate();
		model.addAttribute("counselTimeUpdate", list);

		return "counsel/counselCalender";

	}

	// 상담 단건 조회
	@GetMapping("admin/counselInfo")
	public String counselInsertForm(Model model, CounselVO counselVO) {
		CounselVO findVO = counselService.counselInfo(counselVO);
		model.addAttribute("counselInfo", findVO);
		return "counsel/counselInfo";
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
