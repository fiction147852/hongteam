package com.son.app.counsel.web;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.son.app.lecture.service.Criteria;
import com.son.app.lecture.service.PageDTO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CounselController {

	@Autowired
	CounselService counselService;

	@GetMapping("admin/counsel")
	public String Counsel(Model model) {

		return "counsel/counsel";
	}

	// 상담 스케줄 전체조회
	@GetMapping("admin/counselList")
	public String counselList(Model model, Criteria cri) {
		List<CounselVO> list = counselService.counselList(cri);
		model.addAttribute("counselList", list);

		int total = counselService.lecPageing(cri);
		model.addAttribute("page", new PageDTO(cri, total));
		
		return "counsel/counselList";
	}

	// 상담 스케쥴 전체 보기 - 캘린더, 시간
	@GetMapping("admin/counselCalendar")
	public String counselCalinder(Model model) {
		List<CounselVO> counList = counselService.counselCalendarList();
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
	public List<String> getDayImp(@PathVariable String clickDate) throws ParseException {
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
	public String getWeekImp(@PathVariable String wekDayTime) throws UnsupportedEncodingException {
		AdmissionCounselPossibilityVO vo = new AdmissionCounselPossibilityVO();
		vo.setWeekdaysCode(wekDayTime);
		String list = counselService.getdayPos(vo);

		return list;
	}

	// 처리 - 주간 상담 시간 조율
	@ResponseBody
	@PostMapping("admin/counselWeekTime")
	public int counselWeekTime(@RequestBody List<AdmissionCounselPossibilityVO> admissionCounselPossibilityList,
			Model model) {
		int weekTimeList = counselService.counselWeekTimeUpdate(admissionCounselPossibilityList);

		return weekTimeList;
	}

	// 처리 - 일간 상담 시간 조율
	@ResponseBody
	@PostMapping("admin/counselDayTime")
	public int counselDayTime(@RequestBody List<CounselImpossibilityVO> list) {
		System.out.println(list.get(0));
		System.out.println(list);
		return counselService.counselDayTimeUpdate(list);
	}

	// 상담 단건 조회
	@GetMapping("admin/counselInfo")
	public String counselInsertForm(Model model, CounselVO counselVO) {
		CounselVO findVO = counselService.counselInfo(counselVO);
		model.addAttribute("counselInfo", findVO);
		return "counsel/counselInfo";
	}

	// 상담 단건 수정 - 페이지
	@GetMapping("admin/counselUpdate")
	public String counselUpdateForm(Model model, Integer counselNumber) {
		// 해당 상담 단건 번호
		CounselVO cvo = new CounselVO();
		cvo.setCounselNumber(counselNumber);
		// 해당 번호 정보 단건 조회
		CounselVO findVO = counselService.counselInfo(cvo);

		model.addAttribute("counselInfo", findVO);
		// 해당날짜 가능한 불가능한 시간 제외시킬 시간
		List<CounselVO> impTime = counselService.counselImpTime(findVO);
		List<Integer> impList = new ArrayList<>();
		impTime.forEach(e -> {
			impList.add(Integer.parseInt(e.getTimeCode()));
		});
		log.info(impList.toString());
		// 해당날짜 가능한 시간
		AdmissionCounselPossibilityVO vo = new AdmissionCounselPossibilityVO();

		// 해당 날짜 가져오기
		Date reservationDate = findVO.getReservationDate();
		SimpleDateFormat format1 = new SimpleDateFormat("E요일");
		// 해당 요일 변환
		vo.setWeekdaysCode(format1.format(reservationDate));
		System.out.println(format1);

		// 가능한 time code
		String list = counselService.getdayPos(vo);
		System.out.println("무슨날짜?" + list);
		String[] T = list.split(",");
		int start = Integer.parseInt(T[0].trim());
		int end = Integer.parseInt(T[1].trim());

		// 가능한시간 list 만들기
		List<Integer> posTime = new ArrayList<Integer>();

		for (int i = start; i <= end; i++) {
			posTime.add(i);
		}
		model.addAttribute("timeList", list);

		// posTime 리스트에서 impTime에 포함된 시간들을 제외하는 코드

		System.out.println("무슨시간?" + posTime);
		impList.forEach(e -> {
			if (posTime.indexOf(e) != -1) {
				posTime.remove(posTime.indexOf(e));
			}
		});
		System.out.println("최종 가능 시간?" + posTime);
		model.addAttribute("counselPosTime", posTime);

		return "counsel/counselUpdate";
	}

	@ResponseBody
	@PostMapping("admin/counselUpdateCal")
	public List<Integer> getPosTime(String reservationDate, String today) {
		
		// 불가능시간 리스트 불러오기
		List<Integer> impTime = counselService.timeList(reservationDate);
		System.out.println("불가능시간" + impTime);
		//가능한시간 리스트 불러오기
		AdmissionCounselPossibilityVO posTimeDay = counselService.counselTimeList(today);
		System.out.println("언제" + posTimeDay);
		
		int sTime = Integer.parseInt(posTimeDay.getStartTime());
		int eTime = Integer.parseInt(posTimeDay.getEndTime());
		
		List<Integer> posTimes = new ArrayList<>();
		for(int i=sTime; i <= eTime; i++) {
			posTimes.add(i);
		}
		
		impTime.forEach(e -> {
			if (posTimes.indexOf(e) != -1) {
				posTimes.remove(posTimes.indexOf(e));
			}
		});
		
		
		
		return posTimes;
	}

	// 상담 단건 수정 - 처리
	@ResponseBody
	@PostMapping("admin/counselUpdate") // requestbody같은거 안쓸때는 fetch에서 그냥 데이터 보내기
	public int counselUpdate(CounselVO counselVO) {
		return counselService.counselUpdate(counselVO);

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
