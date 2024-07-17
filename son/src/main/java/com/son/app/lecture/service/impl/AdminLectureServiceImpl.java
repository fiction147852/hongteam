package com.son.app.lecture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.lecture.mapper.AdminLectureMapper;
import com.son.app.lecture.service.AdminLectureService;
import com.son.app.lecture.service.Criteria;
import com.son.app.lecture.service.LectureStudentVO;
import com.son.app.lecture.service.LectureSubjectDetailVO;
import com.son.app.lecture.service.LectureSubjectVO;
import com.son.app.lecture.service.LectureVO;
import com.son.app.lecture.service.RegistrationVO;
import com.son.app.member.service.InstructorVO;

@Service
public class AdminLectureServiceImpl implements AdminLectureService {
	
	@Autowired
	AdminLectureMapper adminLectureMapper;

	// 강의 전체 리스트 불러오기 
	@Override
	public List<LectureVO> adminLectureList(Criteria cri) {
		return adminLectureMapper.adminLectureSelectAll(cri);
	}
	
	// 데이터 갯수
	@Override
	public int lecPageing(Criteria cri) {
		return adminLectureMapper.lecPageing(cri);
	}
	
	// 강사 정보 드롭 리스트 불러오기
	@Override
	public List<InstructorVO> adminInstructorList() {
		return adminLectureMapper.adminInstructorSelectAll();
	}


	
	@Override
	public LectureVO adminLectureInfo(Integer lectureNumber) {
		return adminLectureMapper.adminLectureSelectInfo(lectureNumber);
	}

	@Override
	public int adminLectureInsert(LectureVO lectureVO) {
		return adminLectureMapper.adminLectureInsertInfo(lectureVO);
	}

	@Override
	public List<LectureSubjectVO> adminLectureSubjectList() {
		return adminLectureMapper.adminLectureSubjectList();
	}

	@Override
	public List<LectureSubjectDetailVO> adminLectureSubjectDetailList() {
		return adminLectureMapper.adminLectureSubjectDetailList();
	}

	@Override
	public List<LectureStudentVO> adminLectureStudInfo(Integer lectureNumber) {
		return adminLectureMapper.adminLectureStudentList(lectureNumber);
	}

	@Override
	public LectureStudentVO adminLectureStudEmail(String email) {
		return adminLectureMapper.adminLectureStudentEmail(email);
	}

	@Override
	public int adminLectureStudInsert(LectureStudentVO lectureStudentVO) {
		return adminLectureMapper.adminLectureStudentInsert(lectureStudentVO);
	}

	@Override
	public int adminLectureStudNumInsert(RegistrationVO registrationVO) {
		return adminLectureMapper.adminLectureStudNumInsert(registrationVO);
	}







}