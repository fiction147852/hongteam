package com.son.app.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.board.mapper.InstructorQnaMapper;
import com.son.app.board.service.InstructorQnaService;
import com.son.app.board.service.ParentCounselVO;
import com.son.app.page.PageVO;

@Service
public class InstructorQnaServiceImpl implements InstructorQnaService {
	
	@Autowired
	InstructorQnaMapper mapper;
	
	@Override
	public PageVO getPageInfo(Integer lectureNumber, int page) {
		
		return new PageVO(page, mapper.countQna(lectureNumber), 5, 5);
	}

	@Override
	public List<ParentCounselVO> qnaList(Integer lectureNumber, PageVO pageVO) {
		
		int start = Math.max(1, (pageVO.getPage() - 1) * pageVO.getPageSize());
        int end = start + pageVO.getPageSize();
        
        return mapper.selectCounselAll(lectureNumber, start, end);
        
	}

	@Override
	public ParentCounselVO qnaInfo(Integer counselNumber) {
		
		return mapper.counselInfo(counselNumber);
	}

	@Override
	public int updateQna(ParentCounselVO qvo) {
		
		return mapper.updateQna(qvo);
	}

}
