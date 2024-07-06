package com.son.app.exam.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.son.app.page.PageVO;

public interface InstructorExamService {
	public List<ExamVO> examList(@Param("lectureNumber") Integer lectureNumber, PageVO pageVO);
	public PageVO getPageInfo(@Param("lectureNumber") Integer lectureNumber, int page);
}
