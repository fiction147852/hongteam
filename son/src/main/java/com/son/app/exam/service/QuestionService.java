package com.son.app.exam.service;

import java.util.List;
import java.util.Map;

public interface QuestionService {
	public List<QuestionVO> questionList();
	
	public int insertQuesti(QuestionVO questionVO);
	
	public Map<String, Object> updateQuestion(QuestionVO questionVO);
	
	public int deleteQuestion (int questionNo);
}
