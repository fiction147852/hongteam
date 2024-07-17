package com.son.app.lecture.service;

import java.util.List;

import com.son.app.member.service.InstructorVO;

public interface AdminLectureService {
	
	// 목록 조회 페이징
	public List<LectureVO> adminLectureList(Criteria cri);
	
	// 데이터 갯수
	public int lecPageing(Criteria cri);
	
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
