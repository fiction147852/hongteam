package com.son.app.lecture.mapper;

import java.util.List;

import com.son.app.lecture.service.LectureVO;

public interface InsLectureMapper {
	public List<LectureVO> selectLectureAll();
	
	public LectureVO selectLectureInfo(LectureVO lectureVO);
	
	public int insertLectureInfo(LectureVO lectureVO);
	
	public int updateLetureInfo(LectureVO lectureVO);
	
	public int deleteLetureInfo(int lectureNo);
}
