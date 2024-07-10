package com.son.app.question.mapper;

import java.util.List;
import java.util.Map;

import com.son.app.question.service.QuestionVO;

public interface QuestionMapper {
	// 전체
	public List<QuestionVO> selectQuestionAll();
	
	// 단건
	public QuestionVO selectQuestionInfo(QuestionVO questionVO);

	// 등록
	public int insertQuestionInfo(QuestionVO questionVO);
	
	// 수정
	public int updateQuestionInfo(QuestionVO questionVO);
	
	// 삭제
	public int deleteQuestionInfo(int questionNo);
	
	List<QuestionVO> searchQuestions(Map<String, String> params);

//	public List<Map<String, String>> getAllSubjects();
//
//	public List<Map<String, String>> getDetailSubjects(String subjectCode);
}
