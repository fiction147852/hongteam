package com.son.app.lecture.mapper;

import java.util.List;

import com.son.app.lecture.service.LectureInstructorVO;

public interface ParentLectureMapper {
	//학부모 자녀의 강의정보조회
	public List<LectureInstructorVO> ParentLectureInfoAll();
}
