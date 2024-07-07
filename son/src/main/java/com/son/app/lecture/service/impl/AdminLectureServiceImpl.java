package com.son.app.lecture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.lecture.mapper.AdminLectureMapper;
import com.son.app.lecture.service.AdminLectureService;
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

}