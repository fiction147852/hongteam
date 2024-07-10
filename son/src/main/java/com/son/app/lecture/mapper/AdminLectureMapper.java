package com.son.app.lecture.mapper;

import java.util.List;

import com.son.app.lecture.service.LectureStudentVO;
import com.son.app.lecture.service.LectureSubjectDetailVO;
import com.son.app.lecture.service.LectureSubjectVO;
import com.son.app.lecture.service.LectureVO;
import com.son.app.lecture.service.RegistrationVO;

public interface AdminLectureMapper {
	public List<LectureVO> adminLectureSelectAll();
	
	public LectureVO adminLectureSelectInfo(Integer lectureNumber);

	public int adminLectureInsertInfo(LectureVO lectureVO);

	public List<LectureSubjectVO> adminLectureSubjectList();

	public List<LectureSubjectDetailVO> adminLectureSubjectDetailList();

	public List<LectureStudentVO> adminLectureStudentList(Integer lectureNumber);

	public LectureStudentVO adminLectureStudentEmail(String email);

	public int adminLectureStudentInsert(LectureStudentVO lectureStudentVO);

	public int adminLectureStudNumInsert(RegistrationVO registrationVO);
}
