package com.son.app.lecture.online.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.son.app.page.PageVO;

public interface InsOnlineLectureService {
	public List<OnlineLectureVO> onlineLectureList(@Param("lectureNumber") Integer lectureNumber, PageVO pageVO);
	public PageVO getPageInfo(@Param("lectureNumber") Integer lectureNumber, int page);
	
	public OnlineLectureVO onlineLecsInfo(Integer onlineLectureNumber);
	
}
