package com.son.app.lecture.mapper;

import java.util.List;

import com.son.app.lecture.service.LectureVO;

public interface AdminLectureMapper {
	public List<LectureVO> adminLectureSelectAll();
	
	public LectureVO adminLectureSelectInfo(Integer lectureNumber);
}
