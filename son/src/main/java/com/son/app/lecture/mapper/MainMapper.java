package com.son.app.lecture.mapper;

import java.util.List;

import com.son.app.lecture.service.MainLectureVO;

public interface MainMapper {
	public List<MainLectureVO> selectAll();
}
