package com.son.app.counsel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.counsel.mapper.MainCounselMapper;
import com.son.app.counsel.service.CounselVO;
import com.son.app.counsel.service.MainCounselService;

@Service
public class MainCounselServiceImpl implements MainCounselService {

	@Autowired
	MainCounselMapper mapper;
	
	@Override
	public int mainCounselInsert(CounselVO cvo) {
		return mapper.mainCounselInsert(cvo);
	}
	
}
