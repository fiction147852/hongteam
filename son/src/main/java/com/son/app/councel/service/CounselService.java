package com.son.app.councel.service;

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
	public List<CounselVO> counselCalinder();

	//상담 시간 조율 
	public List<CounselVO> counselTime();
	
}
