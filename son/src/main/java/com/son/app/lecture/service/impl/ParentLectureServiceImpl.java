package com.son.app.lecture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.lecture.mapper.ParentLectureMapper;
import com.son.app.lecture.service.LectureInstructorVO;
import com.son.app.lecture.service.ParentLectureService;

@Service
public class ParentLectureServiceImpl implements ParentLectureService{
	
	@Autowired
	ParentLectureMapper parentlecturemapper;

	@Override
	public List<LectureInstructorVO> ParentLetureInfoList() {
		return parentlecturemapper.ParentLectureInfoAll();
	}	
}
