package com.son.app.counsel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.counsel.mapper.CounselMapper;
import com.son.app.counsel.service.AdmissionCounselPossibilityVO;
import com.son.app.counsel.service.CounselImpossibilityVO;
import com.son.app.counsel.service.CounselService;
import com.son.app.counsel.service.CounselVO;
import com.son.app.lecture.service.Criteria;

@Service
public class CounselServiceImpl implements CounselService {

	@Autowired
	CounselMapper counselMapper;
	// 상담 달력 보기
	@Override
	public List<CounselVO> counselCalendarList() {
		return counselMapper.counselSelectCalendarAll();
	}
	
	
	// 상담일정 전체조회
	@Override
	public List<CounselVO> counselList(Criteria cri) {
		return counselMapper.counselSelectAll(cri);
	}

	// 데이터 갯수
	@Override
	public int lecPageing(Criteria cri) {
		return counselMapper.counselPageing(cri);
	}
	// 상담일정 단건조회
	@Override
	public CounselVO counselInfo(CounselVO counselVO) {
		return counselMapper.counselSelectInfo(counselVO);
	}

	// 등록
	@Override
	public int counselInsert(CounselVO counselVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	// 수정
	@Override
	public int counselUpdate(CounselVO counnselVO) {
		counselMapper.counselUpdateInfo(counnselVO);
		return 0;
	}

	// 삭제
	@Override
	public int counselDelete(int counselNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CounselVO> counselCalendar() {
		// TODO Auto-generated method stub
		return null;
	}

	// 주간 상담 시간 전체 조회
	@Override
	public List<AdmissionCounselPossibilityVO> counselWeekTimeList() {
		return counselMapper.counselWeekTimeSelectAll();
	}

	// 주간 시간 조율
	@Override
	public int counselWeekTimeUpdate(List<AdmissionCounselPossibilityVO> admissionCounselPossibilityList) {
		int time = 0;

		for (int i = 0; i < admissionCounselPossibilityList.size(); i++) {
			int result = counselMapper.counselWeekTimeUpdate(admissionCounselPossibilityList.get(i));
			time = result + time;
		}
		return time;
	}

	// 일간 상담 시간 조율 조회
	@Override
	public List<CounselImpossibilityVO> counselDayTimeList() {
		return counselMapper.counselDayTimeSelectAll();
	}

	// 일간 상담 시간 조율
	@Override
	public int counselDayTimeUpdate(List<CounselImpossibilityVO> list) {

		int result = 0;

		for (CounselImpossibilityVO vo : list) {
			result += counselMapper.counselDayTimeUpdate(vo);
		}
		return result;
	}

	// 클릭시 불가능한 시간대 처리
	@Override
	public List<String> getDatImpList(CounselImpossibilityVO coun) {
		return counselMapper.counselDayImpSelect(coun);
	}

	// 클릭시 가능한 시간대 처리
	@Override
	public String getdayPos(AdmissionCounselPossibilityVO vo) {
		return counselMapper.counselDayPos(vo);
	}

	@Override
	public List<CounselVO> counselImpTime(CounselVO counselVO) {
		return counselMapper.counImpTime(counselVO);
	}

	// 해당 날짜 불가능한시간 뷰리스트
	@Override
	public List<Integer> timeList(String reservationDate) {
		return counselMapper.impTimeList(reservationDate);
	}

	// 해당 날짜 가능한 뷰 리스트
	@Override
	public AdmissionCounselPossibilityVO counselTimeList(String today) {
		return counselMapper.counPosList(today);
	}



}
