package com.son.app.councel.mapper;

import java.util.List;

import com.son.app.councel.service.CounselVO;

public interface CounselMapper {
	
	//상담일정 전체조회
	public List<CounselVO> counselSelectAll();
	
	//상담일정 단건조회 
	public CounselVO counselSelectInfo(CounselVO counselVO);

	//등록
	public int counselInsertInfo(CounselVO counselVO);
	
	//수정
	public int counselUpdateInfo(CounselVO counselVO);
	
	//삭제
	public int counselDeleteInfo(int counselNo);
}
