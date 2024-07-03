package com.son.app.board.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.board.mapper.ParentCounselMapper;
import com.son.app.board.service.LectureNameVO;
import com.son.app.board.service.ParentCounselService;
import com.son.app.board.service.ParentCounselVO;
import com.son.app.member.service.StudentVO;

@Service
public class ParentCounselServiceImpl implements ParentCounselService {
	@Autowired
	ParentCounselMapper parentcounselMapper;

	@Override
	public List<ParentCounselVO> ParentCounselList() {
		return parentcounselMapper.selectParentCounselAll();
	}

	@Override
	public ParentCounselVO ParentCounselInfo(int counselNumber) {
		return parentcounselMapper.selectParentCounselInfo(counselNumber);
	}

	@Override
	public int insertParentCounsel(ParentCounselVO parentcounselVO) {
		int result = parentcounselMapper.insertParentCounsel(parentcounselVO);
		
		return result == 1 ? parentcounselVO.getCounselNumber() : -1;
	}

	@Override
	public Map<String, Object> updateParentCounsel(ParentCounselVO parentcounselVO) {
		Map<String, Object> map = new HashMap<>();
		boolean inSuccessed = false;
		
		int result = parentcounselMapper.updateParentCounsel(parentcounselVO);
		
		if(result == 1) {
			inSuccessed = true;
		}
		
		map.put("result", inSuccessed);
		map.put("target", parentcounselVO);
		return map;
	}

	@Override
	public int deleteParentCounsel(int counselNumber) {
		return parentcounselMapper.deleteParentCounsel(counselNumber);
	}
	@Override
	public List<StudentVO> getStudentsByParent(int parentNumber) {
	    return parentcounselMapper.selectStudentsByParent(parentNumber);
	}

	@Override
	public List<LectureNameVO> StudentInLecture(int studentNumber) {
		return parentcounselMapper.StudentInLecture(studentNumber);
	}

}
