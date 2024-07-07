package com.son.app.exam.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.exam.mapper.InstructorExamMapper;
import com.son.app.exam.service.ExamVO;
import com.son.app.exam.service.InstructorExamService;
import com.son.app.page.PageVO;
import com.son.app.question.service.QuestionVO;

@Service
public class InstructorExamServiceImpl implements InstructorExamService {

	@Autowired
	InstructorExamMapper instructorExamMapper;
	
	@Override
	public List<ExamVO> examList(Integer lectureNumber, PageVO pageVO) {
		int start = (pageVO.getPage() - 1 ) * pageVO.getPageSize() + 1;
		int end = start + pageVO.getPageSize() - 1;
		return instructorExamMapper.selectExamAll(lectureNumber, start, end);
	}

	@Override
	public PageVO getPageInfo(Integer lectureNumber, int page) {
		int totalItems = instructorExamMapper.countExams(lectureNumber);
		return new PageVO(page, totalItems, 5, 5);
	}

	@Override
	public int insertExam(ExamVO examVO) {
		int result = instructorExamMapper.insertExamInfo(examVO);
		
		return result == 1 ? examVO.getTestNumber() : -1;
	}
	
	@Override
    public List<QuestionVO> getQuestionsBySubject(String subjectCode) {
        return instructorExamMapper.selectQuestionsBySubject(subjectCode);
    }
	
	@Override
    public void createExam(Integer lectureNumber, List<Integer> selectedQuestions) {
        // 시험 생성 로직 구현
        ExamVO exam = new ExamVO();
        exam.setLectureNumber(lectureNumber);
        // 기타 필요한 정보 설정
        
        int examId = instructorExamMapper.insertExam(exam);
        
        for (Integer questionId : selectedQuestions) {
            instructorExamMapper.insertExamQuestion(examId, questionId);
        }
    }

	@Override
	public List<QuestionVO> generateExamBySubject(String subjectCode) {
	    List<QuestionVO> exam = new ArrayList<>();
	    
	    // 난이도별 문제 숫자
	    Map<String, Integer> difficultyCount = Map.of(
	        "상", 5,
	        "중", 10,
	        "하", 5
	    );

	    for (Map.Entry<String, Integer> entry : difficultyCount.entrySet()) {
	        String difficulty = entry.getKey();
	        int count = entry.getValue();

	        List<QuestionVO> questions = instructorExamMapper.selectRandomQuestionsBySubjectAndDifficulty(subjectCode, difficulty, count);
	        exam.addAll(questions);
	    }

	    return exam;
	}
	
	@Override
	public int createAndSaveExam(Integer lectureNumber, String subjectCode, String testTitle, Date examinationDate, Date examDate, Integer limitTime) {
	    // 자동 생성된 문제 가져오기
	    List<QuestionVO> questions = generateExamBySubject(subjectCode);
	    
	    // ExamVO 객체 생성 및 설정
	    ExamVO exam = new ExamVO();
	    exam.setLectureNumber(lectureNumber);
	    exam.setTestTitle(testTitle);
	    exam.setExaminationDate(examinationDate);
	    exam.setExamDate(examDate);
	    exam.setLimitTime(limitTime);
	    
	    // 시험 정보 저장
	    int examId = insertExam(exam);
	    
	    // 시험 문제 연결 정보 저장
	    for (QuestionVO question : questions) {
	        instructorExamMapper.insertExamQuestion(examId, question.getQuestionNumber());
	    }
	    
	    return examId;
	}
	
}
