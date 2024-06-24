package com.son.app.exam.mapper;

import java.util.List;

import com.son.app.exam.service.QuestionVO;

public interface QuestionMapper {
	// 전체
	public List<QuestionVO> selectQuestionAll();

	// 등록
	public int insertQuestionInfo(QuestionVO questionVO);
	
	// 수정
	public int updateQuestionInfo(QuestionVO questionVO);
	
	// 삭제
	public int deleteQuestionInfo(int questionNo);
}
