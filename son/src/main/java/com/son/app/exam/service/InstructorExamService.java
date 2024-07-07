package com.son.app.exam.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.son.app.page.PageVO;
import com.son.app.question.service.QuestionVO;

public interface InstructorExamService {
	public List<ExamVO> examList(@Param("lectureNumber") Integer lectureNumber, PageVO pageVO);
	public PageVO getPageInfo(@Param("lectureNumber") Integer lectureNumber, int page);
	
	public int insertExam(ExamVO examVO);
	
    List<QuestionVO> getQuestionsBySubject(String subjectCode);
    List<QuestionVO> generateExamBySubject(String subjectCode);
    
    void createExam(Integer lectureNumber, List<Integer> selectedQuestions);
    int createAndSaveExam(Integer lectureNumber, String subjectCode, String testTitle, Date examinationDate, Date examDate, Integer limitTime);
}
