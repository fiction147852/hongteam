package com.son.app.lecture.mapper;

import java.util.List;

import com.son.app.lecture.service.Criteria;
import com.son.app.lecture.service.LectureStudentVO;
import com.son.app.lecture.service.LectureSubjectDetailVO;
import com.son.app.lecture.service.LectureSubjectVO;
import com.son.app.lecture.service.LectureVO;
import com.son.app.lecture.service.RegistrationVO;
import com.son.app.member.service.InstructorVO;
import com.son.app.member.service.StudentVO;

public interface AdminLectureMapper {
	
	// 목록조회 페이징
	public List<LectureVO> adminLectureSelectAll(Criteria cri);
	
	// 데이터 갯수
	public int lecPageing(Criteria cri);
	
	
	public LectureVO adminLectureSelectInfo(Integer lectureNumber);

	public int adminLectureInsertInfo(LectureVO lectureVO);

	public List<LectureSubjectVO> adminLectureSubjectList();

	public List<LectureSubjectDetailVO> adminLectureSubjectDetailList();

	public List<LectureStudentVO> adminLectureStudentList(Integer lectureNumber);

	public LectureStudentVO adminLectureStudentEmail(String email);

	public int adminLectureStudentInsert(LectureStudentVO lectureStudentVO);

	public int adminLectureStudNumInsert(RegistrationVO registrationVO);

	// 강사 정보 드롭 리스트 불러오기
	public List<InstructorVO> adminInstructorSelectAll();

	
	public List<StudentVO> adminLectureStuListPaging(Criteria cri);
}
