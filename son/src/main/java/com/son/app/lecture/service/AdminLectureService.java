package com.son.app.lecture.service;

import java.util.List;

public interface AdminLectureService {
	public List<LectureVO> adminLectureList();
	
	public LectureVO adminLectureInfo(Integer lectureNumber);

	public List<LectureSubjectVO> adminLectureSubjectList();

	public List<LectureSubjectDetailVO> adminLectureSubjectDetailList();

	public int adminLectureInsert(LectureVO lectureVO);

	public List<LectureStudentVO> adminLectureStudInfo(Integer lectureNumber);

}
