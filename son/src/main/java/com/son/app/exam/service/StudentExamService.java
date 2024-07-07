package com.son.app.exam.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentExamService {

    // 시험 목록
    public List<ExamListVO> examList(@Param("lectureNumber") Integer lectureNumber, @Param("testTitle") String testTitle, @Param("participateStatus") String participateStatus, @Param("startRow") int startRow, @Param("endRow") int endRow);
    public int examCount(@Param("lectureNumber") Integer lectureNumber, @Param("testTitle") String testTitle, @Param("participateStatus") String participateStatus);

    // 시험 상세 정보
    public List<ExamInfoVO> examInfo(@Param("testNumber") Integer testNumber);
}
