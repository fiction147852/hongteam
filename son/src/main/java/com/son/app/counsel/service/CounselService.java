package com.son.app.counsel.service;

import java.util.List;
import java.util.Map;

public interface CounselService {
	
	//상담일정 전체조회
	public List<CounselVO> counselList();
	
	//상담일정 단건조회 
	public CounselVO counselInfo(CounselVO counselVO);

	//등록
	public int counselInsert(CounselVO counselVO);
	
	//수정
	public Map<String, Object> counselUpdate(CounselVO counnselVO);
	
	//삭제
	public int counselDelete(int counselNo);

	//상담 달력 조회 
	public List<CounselVO> counselCalendar();

	// 페이지 - 주간 상담 시간 메뉴 
	public List<AdmissionCounselPossibilityVO> counselWeekTimeList();
	
	// 처리 - 주간 시간 조율 
	public 	int counselWeekTimeUpdate(List<AdmissionCounselPossibilityVO> admissionCounselPossibilityList);

	
	// 페이지 - 일간 상담 시간 메뉴 
	public List<CounselImpossibility> counselDayTimeList();
	
	//처리 - 일간 시간 노율
	public int counselDayTimeUpdate(List<CounselImpossibility> admissionCounselPossibilityList);




	
}
