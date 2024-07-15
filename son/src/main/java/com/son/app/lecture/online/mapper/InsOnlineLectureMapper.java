package com.son.app.lecture.online.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.son.app.lecture.online.service.OnlineLectureVO;

public interface InsOnlineLectureMapper {
	public List<OnlineLectureVO> selectOnlineLectureAll(@Param("lectureNumber") Integer lectureNumber,
														@Param("start") int start,
														@Param("end") int end);
	int countOnlineLecs(@Param("lectureNumber") Integer lectureNumber);
	
	public OnlineLectureVO selectLectureInfo(@Param("onlineLectureNumber") Integer onlineLectureNumber);
	
	public int insertOnlineLectureInfo(OnlineLectureVO onlineLectureVO);
	
	public int updateOnlineLectureInfo(OnlineLectureVO onlineLectureVO);
	
	public int deleteOnlineLectureInfo(int onlineLectureNo);
}
