package com.son.app.board.mapper;

import java.util.List;

import com.son.app.board.service.ParentCounselVO;

public interface ParentCounselMapper {
    // 전체
    List<ParentCounselVO> selectParentCounselAll();

	// 단건
	public ParentCounselVO selectParentCounselInfo(ParentCounselVO parentcounselVO);
	
	// 등록
	public int insertParentCounsel(ParentCounselVO parentcounselVO);
	
	// 수정
	public int updateParentCounsel(ParentCounselVO parentcounselVO);
	
	// 삭제
	public int deleteParentCounsel(int counselNumber);
}
