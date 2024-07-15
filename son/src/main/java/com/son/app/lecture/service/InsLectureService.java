package com.son.app.lecture.service;

import java.util.List;

public interface InsLectureService {
	public List<LectureVO> lectureList();
	
	public LectureVO lectureInfo(Integer lectureNumber);
	
	List<Integer> getStudentNumbersByLecture(Integer lectureNumber);
}
