package com.son.app.lecture.service;

import java.util.List;
import java.util.Map;

public interface InsLectureService {
	public List<LectureVO> lectureList();
	
	public LectureVO lectureInfo(LectureVO lectureVO);
	
	public int insertLecture(LectureVO lectureVO);
	
	public Map<String, Object> updateLecture(LectureVO lectureVO);
	
	public int deleteLecture (int lectureNo);
	
	List<Integer> getStudentNumbersByLecture(Integer lectureNumber);
}
