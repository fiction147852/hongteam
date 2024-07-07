package com.son.app.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.son.app.exam.service.ExamVO;
import com.son.app.question.service.QuestionVO;

public interface InstructorExamMapper {
	public List<ExamVO> selectExamAll(@Param("lectureNumber") Integer lectureNumber,
									  @Param("start") int start,
									  @Param("end") int end);
	int countExams(@Param("lectureNumber") Integer lectureNumber);
	
	public int insertExamInfo(ExamVO examVO);
	
	int insertExam(ExamVO examVO);
    void insertExamQuestion(@Param("examId") int examId, @Param("questionId") int questionId);
	
	List<QuestionVO> selectQuestionsBySubject(String subjectCode);
    List<QuestionVO> selectRandomQuestionsBySubjectAndDifficulty(@Param("subjectCode") String subjectCode, 
													             @Param("difficulty") String difficulty, 
													             @Param("count") int count);
}
