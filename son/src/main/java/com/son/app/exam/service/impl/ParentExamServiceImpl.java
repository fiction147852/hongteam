package com.son.app.exam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.exam.mapper.ParentExamMapper;
import com.son.app.exam.service.ExamInfoVO;
import com.son.app.exam.service.GradingResult;
import com.son.app.exam.service.ParentChildExamVO;
import com.son.app.exam.service.ParentExamService;
@Service
public class ParentExamServiceImpl implements ParentExamService{
	
    @Autowired
    private ParentExamMapper parentExamMapper;

    @Override
    public List<ParentChildExamVO> childexamList(Integer studentNumber, Integer lectureNumber) {
		return parentExamMapper.childExamList(studentNumber, lectureNumber);
    }

    @Override
    public int examCount(Integer lectureNumber, String testTitle, String participateStatus) {
        return parentExamMapper.studentExamCount(lectureNumber, testTitle, participateStatus);
    }

    @Override
    public List<ExamInfoVO> examInfo(Integer testNumber) {
        return parentExamMapper.studentExamInfo(testNumber);
    }

    @Override
    public void autoGradeExam(GradingResult gradingResultList) {
    	parentExamMapper.getExamResults(gradingResultList);
    }

    @Override
    public int modifyParticipateStatus(Integer participateNumber) {
        return parentExamMapper.modifyParticipateStatus(participateNumber);
    }

    @Override
    public List<GradingResult> getChildExamResults(Integer participateNumber) {
        return parentExamMapper.getChildExamResults(participateNumber);
    }

	
	
}
