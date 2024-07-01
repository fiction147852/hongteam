package com.son.app.counsel.web;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.son.app.counsel.service.AdmissionCounselPossibilityVO;
import com.son.app.counsel.service.CounselImpossibilityVO;
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

	// 상담 스케쥴 전체 보기 - 캘린더, 시간
	@GetMapping("admin/counselCalendar")
	public String counselCalinder(Model model) {
		List<CounselVO> counList = counselService.counselList();
		model.addAttribute("counselList", counList);
		System.out.println(counList);
		
		List<AdmissionCounselPossibilityVO> counTimeList = counselService.counselWeekTimeList();
		model.addAttribute("counselTime", counTimeList);

		List<CounselImpossibilityVO> counNTimeList = counselService.counselDayTimeList();
		model.addAttribute("counselDayTime", counNTimeList);

		return "counsel/counselCalendar";

	}
	
	// 해당 날짜 불가능한 시간 조회 
	@ResponseBody
	@GetMapping("admin/ad/{clickDate}")
	public List<String> getDayImp(@PathVariable String clickDate) throws ParseException{
		CounselImpossibilityVO vo = new CounselImpossibilityVO();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		vo.setReservationDate(formatter.parse(clickDate));
		System.out.println(vo.toString());
		List<String> list = counselService.getDatImpList(vo);
		
		return list;
	}
	
	
	// 해당 날짜 가능한 시간대 조회
	@ResponseBody
	@GetMapping("admin/wkday/{wekDayTime}")
	public String getWeekImp(@PathVariable String wekDayTime) throws UnsupportedEncodingException{
		AdmissionCounselPossibilityVO vo = new AdmissionCounselPossibilityVO();
		vo.setWeekdaysCode(wekDayTime);
		String list = counselService.getdayPos(vo);
		
		return list;
	}
	
	// 처리 - 주간 상담 시간 조율 
	@ResponseBody
	@PostMapping("admin/counselWeekTime")
	public int counselWeekTime(@RequestBody List<AdmissionCounselPossibilityVO> admissionCounselPossibilityList , Model model) {
		int weekTimeList = counselService.counselWeekTimeUpdate(admissionCounselPossibilityList);

		return weekTimeList;
	}

	
	// 처리 - 일간 상담 시간 조율 
	@ResponseBody
	@PostMapping("admin/counselDayTime")
	public int counselDayTime(@RequestBody List<CounselImpossibilityVO> list) {
		System.out.println(list.get(0));
	    return counselService.counselDayTimeUpdate(list);
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
