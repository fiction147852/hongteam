package com.son.app.lecture.online.mapper;

import java.util.List;

import com.son.app.lecture.online.service.OnlineLectureVO;

public interface InsOnlineLectureMapper {
	public List<OnlineLectureVO> selectOnlineLectureAll();
	
	public OnlineLectureVO selectLectureInfo(OnlineLectureVO onlineLectureVO);
	
	public int insertOnlineLectureInfo(OnlineLectureVO onlineLectureVO);
	
	public int updateOnlineLectureInfo(OnlineLectureVO onlineLectureVO);
	
	public int deleteOnlineLectureInfo(int onlineLectureNo);
}
