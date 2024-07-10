package com.son.app.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.member.mapper.ParentMapper;
import com.son.app.member.service.ParentService;
import com.son.app.member.service.ParentVO;
import com.son.app.member.service.StudentVO;

@Service
public class ParentServiceImpl implements ParentService{
	@Autowired
	ParentMapper parentmapper;
	
	// 전체조회
	@Override
	public List<StudentVO> ParentInfoList(int studentNumber) {
		return parentmapper.childInfoAll(studentNumber);
	}

	@Override
	public List<StudentVO> getStudentsByParentNumber(int parentNumber) {
		return parentmapper.getStudentsByParentNumber(parentNumber);
	}

	@Override
	public int getParentNumberByEmail(String email) {
		return parentmapper.getParentNumberByEmail(email);
	}

	@Override
	public List<ParentVO> mypageInfo(int parentNumber) {
		return parentmapper.mypageInfo(parentNumber);
	}

	@Override
	public int save(ParentVO parent) {
		return parentmapper.saves(parent);
		
	}
	
}
