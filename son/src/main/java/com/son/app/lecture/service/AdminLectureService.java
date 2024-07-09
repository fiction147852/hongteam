package com.son.app.lecture.service;

import java.util.List;
import java.util.Map;

public interface AdminLectureService {
	public List<LectureVO> adminLectureList();
	
	public LectureVO adminLectureInfo(Integer lectureNumber);

}
