package com.son.app.lecture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.lecture.mapper.MainMapper;
import com.son.app.lecture.service.MainLectureService;
import com.son.app.lecture.service.MainLectureVO;

@Service
public class MainLectureServiceImpl implements MainLectureService {
	
	@Autowired
	MainMapper mapper;
	
	@Override
	public List<MainLectureVO> selectAll() {
		return mapper.selectAll();
	}

}
