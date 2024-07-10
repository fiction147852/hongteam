package com.son.app.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.son.app.exam.service.ExamVO;
import com.son.app.exam.service.GradingResult;

public interface InstructorExamMapper {
	public List<ExamVO> selectExamAll(@Param("lectureNumber") Integer lectureNumber,
									  @Param("start") int start,
									  @Param("end") int end);
	int countExams(@Param("lectureNumber") Integer lectureNumber);
	
    List<ExamVO> selectCompletedStudents(@Param("testNumber") Integer testNumber,
							             @Param("start") int start,
							             @Param("end") int end);
	int countCompletedStudents(@Param("testNumber") Integer testNumber);
	
	List<GradingResult> getStudentExamResults(@Param("participateNumber") Integer participateNumber);


	
	void insertExam(ExamVO exam);
}
