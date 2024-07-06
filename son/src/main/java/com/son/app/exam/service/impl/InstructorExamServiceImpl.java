package com.son.app.exam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.exam.mapper.InstructorExamMapper;
import com.son.app.exam.service.ExamVO;
import com.son.app.exam.service.InstructorExamService;
import com.son.app.page.PageVO;

@Service
public class InstructorExamServiceImpl implements InstructorExamService {

	@Autowired
	InstructorExamMapper instructorExamMapper;
	
	@Override
	public List<ExamVO> examList(Integer lectureNumber, PageVO pageVO) {
		int start = (pageVO.getPage() - 1 ) * pageVO.getPageSize() + 1;
		int end = start + pageVO.getPageSize() - 1;
		return instructorExamMapper.selectExamAll(lectureNumber, start, end);
	}

	@Override
	public PageVO getPageInfo(Integer lectureNumber, int page) {
		int totalItems = instructorExamMapper.countExams(lectureNumber);
		return new PageVO(page, totalItems, 5, 5);
	}
	
	
}
