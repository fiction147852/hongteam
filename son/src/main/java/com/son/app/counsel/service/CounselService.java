package com.son.app.counsel.service;

import java.util.List;

import com.son.app.lecture.service.Criteria;

public interface CounselService {

	// 상담일정 전체조회
	public List<CounselVO> counselList(Criteria cri);
	
	// 데이터 갯수
	public int lecPageing(Criteria cri);

	// 상담일정 단건조회
	public CounselVO counselInfo(CounselVO counselVO);

	// 등록
	public int counselInsert(CounselVO counselVO);

	// 수정
	public int counselUpdate(CounselVO counnselVO);

	// 삭제
	public int counselDelete(int counselNo);

	// 상담 달력 조회
	public List<CounselVO> counselCalendar();

	// 페이지 - 주간 상담 시간 메뉴
	public List<AdmissionCounselPossibilityVO> counselWeekTimeList();

	// 처리 - 주간 시간 조율
	public int counselWeekTimeUpdate(List<AdmissionCounselPossibilityVO> admissionCounselPossibilityList);

	// 페이지 - 일간 상담 시간 메뉴
	public List<CounselImpossibilityVO> counselDayTimeList();

	// 처리 - 일간 시간 조율
	public int counselDayTimeUpdate(List<CounselImpossibilityVO> list);

	// 클릭시 불가능 시간 처리
	public List<String> getDatImpList(CounselImpossibilityVO Coun);

	// 클릭시 가능한 시간대 처리
	public String getdayPos(AdmissionCounselPossibilityVO vo);

	public List<CounselVO> counselImpTime(CounselVO counselVO);
	
	
	// 해당 날짜 불가능한시간 뷰리스트
	public List<Integer> timeList(String reservationDate);

	// 해당 날짜 가능한 뷰 리스트
	public AdmissionCounselPossibilityVO counselTimeList(String today);

	public List<CounselVO> counselCalendarList();

}
