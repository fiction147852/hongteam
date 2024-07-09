package com.son.app.lecture.mapper;

import java.util.List;

import com.son.app.lecture.service.LectureStudentVO;
import com.son.app.lecture.service.LectureSubjectDetailVO;
import com.son.app.lecture.service.LectureSubjectVO;
import com.son.app.lecture.service.LectureVO;

public interface AdminLectureMapper {
	public List<LectureVO> adminLectureSelectAll();
	
	public LectureVO adminLectureSelectInfo(Integer lectureNumber);

	public LectureVO adminLectureInsertInfo(LectureVO lectureVO);

	public List<LectureSubjectVO> adminLectureSubjectList();

	public List<LectureSubjectDetailVO> adminLectureSubjectDetailList();

	public LectureStudentVO adminLectureStudentList(Integer lectureNumber);
}
