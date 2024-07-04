package com.son.app.exam.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentExamService {

    public List<ExamListVO> examList(@Param("lectureNumber") Integer lectureNumber, @Param("testTitle") String testTitle, @Param("participateStatus") String participateStatus, @Param("startRow") int startRow, @Param("endRow") int endRow);
    public int examCount(@Param("lectureNumber") Integer lectureNumber, @Param("testTitle") String testTitle, @Param("participateStatus") String participateStatus);
}
