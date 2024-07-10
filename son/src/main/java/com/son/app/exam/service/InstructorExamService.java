package com.son.app.exam.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.son.app.page.PageVO;

public interface InstructorExamService {
	public List<ExamVO> examList(@Param("lectureNumber") Integer lectureNumber, PageVO pageVO);
	public PageVO getPageInfo(@Param("lectureNumber") Integer lectureNumber, int page);
	
	List<ExamVO> getCompletedStudentList(@Param("testNumber") Integer testNumber, PageVO pageVO);
	PageVO getCompletedStudentPageInfo(@Param("testNumber") Integer testNumber, int page);
	
	void createExam(ExamVO exam);
}
