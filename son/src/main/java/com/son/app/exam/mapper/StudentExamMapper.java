package com.son.app.exam.mapper;

import com.son.app.exam.service.ExamListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentExamMapper {

    public List<ExamListVO> studentExamList(@Param("lectureNumber") Integer lectureNumber, @Param("testTitle") String testTitle, @Param("participateStatus") String participateStatus ,@Param("startRow") int startRow, @Param("endRow") int endRow);
    public int studentExamCount(@Param("lectureNumber") Integer lectureNumber, @Param("testTitle") String testTitle, @Param("participateStatus") String participateStatus);
}
