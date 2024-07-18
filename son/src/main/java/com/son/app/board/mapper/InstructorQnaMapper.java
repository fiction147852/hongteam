package com.son.app.board.mapper;

import java.util.List;

import com.son.app.board.service.ParentCounselVO;

public interface InstructorQnaMapper {
	public int countQna(Integer lectureNumber);
	public List<ParentCounselVO> selectCounselAll(Integer lectureNumber, int start, int end);
	public ParentCounselVO counselInfo(Integer counselNumber);
}
