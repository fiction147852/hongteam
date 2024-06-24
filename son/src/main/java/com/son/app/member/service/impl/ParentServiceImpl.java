package com.son.app.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.member.mapper.ParentMapper;
import com.son.app.member.service.ParentService;
import com.son.app.member.service.StudentVO;

@Service
public class ParentServiceImpl implements ParentService{
	@Autowired
	ParentMapper childinfomapper;
	
	// 전체조회
	@Override
	public List<StudentVO> ChildInfoList() {
		// TODO Auto-generated method stub
		return childinfomapper.childInfoAll();
	}

}
