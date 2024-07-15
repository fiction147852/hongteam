package com.son.app.lecture.service;

import java.util.List;

import com.son.app.member.service.InstructorVO;

public interface AdminLectureService {
	public List<LectureVO> adminLectureList();
	
	public LectureVO adminLectureInfo(Integer lectureNumber);

	public List<LectureSubjectVO> adminLectureSubjectList();

	public List<LectureSubjectDetailVO> adminLectureSubjectDetailList();

	public int adminLectureInsert(LectureVO lectureVO);

	public List<LectureStudentVO> adminLectureStudInfo(Integer lectureNumber);

	public LectureStudentVO adminLectureStudEmail(String email);

	public int adminLectureStudInsert(LectureStudentVO lectureStudentVO);

	public int adminLectureStudNumInsert(RegistrationVO registrationVO);

	// 강사 정보 드롭 리스트 불러오기
	public List<InstructorVO> adminInstructorList();

}
