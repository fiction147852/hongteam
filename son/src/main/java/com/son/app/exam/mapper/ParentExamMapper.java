// ParentExamMapper.java
package com.son.app.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.son.app.exam.service.ExamInfoVO;
import com.son.app.exam.service.ExamListVO;
import com.son.app.exam.service.GradingResult;
import com.son.app.exam.service.ParentChildExamVO;

public interface ParentExamMapper {

    // 시험 목록
	public List<ParentChildExamVO> childExamList(Integer studentNumber, Integer lectureNumber);
	
    public int studentExamCount(@Param("lectureNumber") Integer lectureNumber, @Param("testTitle") String testTitle, @Param("participateStatus") String participateStatus);

    // 시험지
    public List<ExamInfoVO> studentExamInfo(@Param("testNumber") Integer testNumber);

    // 시험 결과
    public int getExamResults(GradingResult gradingResultList);
    public int modifyParticipateStatus(Integer participateNumber);
    
    List<GradingResult> getChildExamResults(@Param("participateNumber") Integer participateNumber);
}
