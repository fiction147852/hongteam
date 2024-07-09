package com.son.app.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.son.app.exam.service.ExamVO;

public interface InstructorExamMapper {
	public List<ExamVO> selectExamAll(@Param("lectureNumber") Integer lectureNumber,
									  @Param("start") int start,
									  @Param("end") int end);
	int countExams(@Param("lectureNumber") Integer lectureNumber);
	
	void insertExam(ExamVO exam);
}
