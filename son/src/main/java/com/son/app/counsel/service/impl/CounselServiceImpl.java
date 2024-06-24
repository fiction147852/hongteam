package com.son.app.counsel.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.counsel.mapper.CounselMapper;
import com.son.app.counsel.service.CounselService;
import com.son.app.counsel.service.CounselVO;

@Service
public class CounselServiceImpl implements CounselService{

	@Autowired
	CounselMapper counselMapper;
	
	//상담일정 전체조회
	@Override
	public List<CounselVO> counselList() {
		return counselMapper.counselSelectAll();
	}
	
	//상담일정 단건조회 
	@Override
	public CounselVO counselInfo(CounselVO counselVO) {
		return counselMapper.counselSelectInfo(counselVO);
	}

	//등록
	@Override
	public int counselInsert(CounselVO counselVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	//수정
	@Override
	public Map<String, Object> counselUpdate(CounselVO counnselVO) {
		// TODO Auto-generated method stub
		return null;
	}

	//삭제 
	@Override
	public int counselDelete(int counselNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CounselVO> counselCalinder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CounselVO> counselTime() {
		// TODO Auto-generated method stub
		return null;
	}

}
