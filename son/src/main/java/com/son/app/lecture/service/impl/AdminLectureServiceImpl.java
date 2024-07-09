package com.son.app.lecture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.lecture.mapper.AdminLectureMapper;
import com.son.app.lecture.service.AdminLectureService;
import com.son.app.lecture.service.LectureStudentVO;
import com.son.app.lecture.service.LectureSubjectDetailVO;
import com.son.app.lecture.service.LectureSubjectVO;
import com.son.app.lecture.service.LectureVO;

@Service
public class AdminLectureServiceImpl implements AdminLectureService {
	
	@Autowired
	AdminLectureMapper adminLectureMapper;

	@Override
	public List<LectureVO> adminLectureList() {
		return adminLectureMapper.adminLectureSelectAll();
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

}