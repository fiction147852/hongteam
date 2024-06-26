package com.son.app.board.mapper;

import java.util.List;

import com.son.app.board.service.ParentCounselVO;

public interface ParentCounselMapper {
    // 전체
    List<ParentCounselVO> selectParentCounselAll();

	// 단건
	public ParentCounselVO selectParentCounselInfo(ParentCounselVO parentcounselVO);
	
	// 등록
	public int insertParentCounselInfo(ParentCounselVO parentcounselVO);
	
	// 수정
	public int updateParentCounselInfo(ParentCounselVO parentcounselVO);
	
	// 삭제
	public int deleteParentCounselInfo(int counselNumber);
}
