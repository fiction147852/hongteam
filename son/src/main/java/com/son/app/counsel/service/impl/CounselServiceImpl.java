package com.son.app.counsel.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.counsel.mapper.CounselMapper;
import com.son.app.counsel.service.AdmissionCounselPossibilityVO;
import com.son.app.counsel.service.CounselImpossibilityVO;
import com.son.app.counsel.service.CounselService;
import com.son.app.counsel.service.CounselVO;

@Service
public class CounselServiceImpl implements CounselService {

	@Autowired
	CounselMapper counselMapper;

	// 상담일정 전체조회
	@Override
	public List<CounselVO> counselList() {
		return counselMapper.counselSelectAll();
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
	public Map<String, Object> counselUpdate(CounselVO counnselVO) {
		Map<String, Object> map = new HashMap<>();

		counselMapper.counselUpdateInfo(counnselVO);
		return map;
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

}
