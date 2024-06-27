package com.son.app.question.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.question.mapper.QuestionMapper;
import com.son.app.question.service.QuestionService;
import com.son.app.question.service.QuestionVO;

@Service
public class QuestionServiceImpl implements QuestionService{
	
	@Autowired
	QuestionMapper questionMapper;

	@Override
	public List<QuestionVO> questionList() {
		return questionMapper.selectQuestionAll();
	}
	
	@Override
	public QuestionVO questionInfo(QuestionVO questionVO) {
		return questionMapper.selectQuestionInfo(questionVO);
	}

	@Override
	public int insertQuestion(QuestionVO questionVO) {
		int result = questionMapper.insertQuestionInfo(questionVO);
		
		return result == 1 ? questionVO.getQuestionNumber() : -1;
	}

	@Override
	public Map<String, Object> updateQuestion(QuestionVO questionVO) {
		Map<String, Object> map = new HashMap<>();
		boolean inSuccessed = false;
		
		int result = questionMapper.updateQuestionInfo(questionVO);
		
		if(result == 1) {
			inSuccessed = true;
		}
		
		map.put("result", inSuccessed);
		map.put("target", questionVO);
		
		return map;
	}

	@Override
	public int deleteQuestion(int questionNo) {
		return questionMapper.deleteQuestionInfo(questionNo);
	}

	
//	 @Override
//	    public List<Map<String, String>> getAllSubjects() {
//	        return questionMapper.getAllSubjects();
//	    }
//
//	    @Override
//	    public List<Map<String, String>> getDetailSubjects(String subjectCode) {
//	        return questionMapper.getDetailSubjects(subjectCode);
//	    }

	
}
