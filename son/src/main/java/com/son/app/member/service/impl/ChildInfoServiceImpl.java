package com.son.app.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.member.mapper.ChildInfoMapper;
import com.son.app.member.service.ChildInfoService;
import com.son.app.member.service.ChildInfoVO;

@Service
public class ChildInfoServiceImpl implements ChildInfoService{
	@Autowired
	ChildInfoMapper childinfomapper;
	
	// 전체조회
	@Override
	public List<ChildInfoVO> ChildInfoList() {
		// TODO Auto-generated method stub
		return childinfomapper.childInfoAll();
	}

}
