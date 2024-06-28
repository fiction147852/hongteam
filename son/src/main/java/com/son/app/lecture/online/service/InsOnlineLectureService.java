package com.son.app.lecture.online.service;

import java.util.List;
import java.util.Map;

public interface InsOnlineLectureService {
	public List<OnlineLectureVO> onlineLectureList();
	
	public OnlineLectureVO onlineLecsInfo(OnlineLectureVO onlineLectureVO);
	
	public int insertOnlineLecture(OnlineLectureVO onlineLectureVO);
	
	public Map<String, Object> updateOnlineLecture(OnlineLectureVO onlineLectureVO);
	
	public int deleteOnlineLecture(int onlineLectureNo);
}
