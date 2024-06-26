package com.son.app.board.service;

import java.util.List;
import java.util.Map;

public interface ParentCounselService {
	// 학부모 계정에서의 학부모 QNA게시판 조회
	public List<ParentCounselVO> ParentCounselList();
	
	public ParentCounselVO ParentCounselInfo(ParentCounselVO parentcounselVO);
	
	public int insertParentCounsel(ParentCounselVO parentcounselVO);
	
	public Map<String, Object> updateParentCounsel(ParentCounselVO parentcounselVO);
	
	public int deleteParentCounsel (int taskNo);
}
