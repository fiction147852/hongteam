package com.son.app.paper.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.son.app.paper.service.PaperVO;
import com.son.app.question.service.QuestionVO;

public interface PaperMapper {
	public List<PaperVO> selectPaperAll(@Param("start") int start,
										@Param("end") int end);
	int countPaper();
	
	public PaperVO selectPaperInfo(int paperNumber);
	
    // 과목별 문제 조회
    List<QuestionVO> selectQuestionsBySubject(@Param("subjectCode") String subjectCode);
    
    // 과목과 난이도별 랜덤 문제 조회
    List<QuestionVO> selectRandomQuestionsBySubjectAndDifficulty(
														        @Param("subjectCode") String subjectCode,
														        @Param("difficulty") String difficulty,
														        @Param("count") int count
														    );
    
    // 시험지 정보 삽입
    int insertPaper(PaperVO paperVO);
    
    // 시험지-문제 연결 정보 삽입
    void insertPaperQuestion(
					        @Param("paperNumber") int paperNumber,
					        @Param("questionNumber") int questionNumber,
					        @Param("score") int score
					    );
}
