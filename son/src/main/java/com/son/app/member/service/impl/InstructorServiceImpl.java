package com.son.app.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.member.mapper.InstructorMapper;
import com.son.app.member.service.InstructorService;
import com.son.app.member.service.InstructorVO;

@Service
public class InstructorServiceImpl implements InstructorService {
	
	@Autowired
	InstructorMapper instructorMapper;

	@Override
	public InstructorVO getInstructorByUsername(String name) {
		return instructorMapper.findByUsername(name);
	}

	@Override
	public InstructorVO getInstructorByNumber(Integer instructorNumber) {
		return instructorMapper.findByNumber(instructorNumber);
	}

	@Override
	public List<InstructorVO> getAllInstructors() {
		 return instructorMapper.findAll();
	}
}
