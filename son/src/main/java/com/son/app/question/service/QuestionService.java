package com.son.app.question.service;

import java.util.List;
import java.util.Map;

public interface QuestionService {
	public List<QuestionVO> questionList();
	
	public QuestionVO questionInfo(QuestionVO questionVO);
	
	public int insertQuestion(QuestionVO questionVO);
	
	public Map<String, Object> updateQuestion(QuestionVO questionVO);
	
	public int deleteQuestion (int questionNo);
	
	List<QuestionVO> searchQuestions(Map<String, String> params);
	
	/*
	 * List<Map<String, String>> getAllSubjects(); List<Map<String, String>>
	 * getDetailSubjects(String subjectCode);
	 */
}
