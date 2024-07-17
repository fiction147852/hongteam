package com.son.app.counsel.mapper;

import java.util.List;

import com.son.app.counsel.service.AdmissionCounselPossibilityVO;
import com.son.app.counsel.service.CounselImpossibilityVO;
import com.son.app.counsel.service.CounselVO;
import com.son.app.lecture.service.Criteria;

public interface CounselMapper {
	
	//
	public List<CounselVO> counselSelectCalendarAll();
	
	//상담일정 전체조회 (페이징)
	public List<CounselVO> counselSelectAll(Criteria cri);
	
	// 데이터 갯수
	public int counselPageing(Criteria cri);
	
	//상담일정 단건조회 
	public CounselVO counselSelectInfo(CounselVO counselVO);

	//등록
	public int counselInsertInfo(CounselVO counselVO);
	
	// 상담 수정
	public int counselUpdateInfo(CounselVO counselVO);
	
	//삭제
	public int counselDeleteInfo(int counselNo);

	
	

	
	//주간 상담 시간 조회
	public List<AdmissionCounselPossibilityVO> counselWeekTimeSelectAll();
	
	//주간 상담 시간 수정
	public int counselWeekTimeUpdate(AdmissionCounselPossibilityVO admissionCounselPossibilityVO);

	//일간 시간 조회
	public List<CounselImpossibilityVO> counselDayTimeSelectAll();
	
	// 해당 일간 불가능한 시간조회
	public List<String> counselDayImpSelect(CounselImpossibilityVO counselImpossibilitVO);
	
	
	//일간 시간 수정
	public int counselDayTimeUpdate(CounselImpossibilityVO vo);

	
	// 해당 일간 가능한 시간대 조회 
	public String counselDayPos(AdmissionCounselPossibilityVO admissionCounselPossibilityVO);

	public List<CounselVO> counImpTime(CounselVO counselVO);
	
	// 해당 날짜 불가능한시간 뷰 리스트
	public List<Integer> impTimeList(String reservationDate);

	// 해당 날짜 가능한 시간 뷰 리스트
	public AdmissionCounselPossibilityVO counPosList(String today);


	
	
}
