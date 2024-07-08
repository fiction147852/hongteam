package com.son.app.exam.mapper;

import com.son.app.exam.service.ExamInfoVO;
import com.son.app.exam.service.ExamListVO;
import com.son.app.exam.service.GradingResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentExamMapper {

    // 시험 목록
    public List<ExamListVO> studentExamList(@Param("lectureNumber") Integer lectureNumber, @Param("testTitle") String testTitle, @Param("participateStatus") String participateStatus ,@Param("startRow") int startRow, @Param("endRow") int endRow);
    public int studentExamCount(@Param("lectureNumber") Integer lectureNumber, @Param("testTitle") String testTitle, @Param("participateStatus") String participateStatus);

    // 시험지
    public List<ExamInfoVO> studentExamInfo(@Param("testNumber") Integer testNumber);

    // 시험 결과
    public int getExamResults(GradingResult gradingResultList);
    public int modifyParticipateStatus(Integer participateNumber);
}
