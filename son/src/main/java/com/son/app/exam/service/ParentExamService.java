package com.son.app.exam.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.son.app.lecture.service.RegistrationVO;

public interface ParentExamService {

    // 시험 목록
    public List<ParentChildExamVO> childexamList(@Param("studentNumber") Integer studentNumber, @Param("lectureNumber") Integer lectureNumber);
    public int examCount(@Param("lectureNumber") Integer lectureNumber, @Param("testTitle") String testTitle, @Param("participateStatus") String participateStatus);

    // 시험 상세 정보
    public List<ExamInfoVO> examInfo(@Param("testNumber") Integer testNumber);

    // 시험 결과
    public void autoGradeExam(GradingResult gradingResultList);
    public int modifyParticipateStatus(Integer participateNumber);
	
    List<GradingResult> getChildExamResults(Integer participateNumber);
	public List<RegistrationVO> childlecture(Integer lectureNumber);
}
