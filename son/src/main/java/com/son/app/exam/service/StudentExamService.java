package com.son.app.exam.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentExamService {

    // 시험 목록
    public List<ExamListVO> examList(Integer lectureNumber, Integer studentNumber, String testTitle, String participateStatus, int startRow, int endRow);
    public int examCount(Integer lectureNumber, Integer studentNumber, String testTitle, String participateStatus);

    // 시험 상세 정보
    public List<ExamInfoVO> examInfo(Integer testNumber);

    // 시험 결과
    public void autoGradeExam(GradingResult gradingResultList);
    public int modifyParticipateStatus(Integer participateNumber, Integer paperNumber);
}
