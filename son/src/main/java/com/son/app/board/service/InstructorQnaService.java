package com.son.app.board.service;

import java.util.List;

import com.son.app.page.PageVO;

public interface InstructorQnaService {
	
	public PageVO getPageInfo(Integer lectureNumber, int page);
	
	public List<ParentCounselVO> qnaList(Integer lectureNumber, PageVO pageVO);
	
	public ParentCounselVO qnaInfo(Integer counselNumber);
	
	public int updateQna(ParentCounselVO qvo);
	
}
