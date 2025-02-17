package com.son.app.exam.service.impl;

import com.son.app.exam.mapper.StudentExamMapper;
import com.son.app.exam.service.ExamInfoVO;
import com.son.app.exam.service.ExamListVO;
import com.son.app.exam.service.GradingResult;
import com.son.app.exam.service.StudentExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentExamServiceImpl implements StudentExamService {

    @Autowired
    private StudentExamMapper studentExamMapper;

    @Override
    public List<ExamListVO> examList(Integer lectureNumber, Integer studentNumber, String testTitle, String participateStatus , int startRow, int endRow) {
        return studentExamMapper.studentExamList(lectureNumber, studentNumber, testTitle, participateStatus, startRow, endRow);
    }

    @Override
    public int examCount(Integer lectureNumber, Integer studentNumber, String testTitle, String participateStatus) {
        return studentExamMapper.studentExamCount(lectureNumber, studentNumber, testTitle, participateStatus);
    }

    @Override
    public List<ExamInfoVO> examInfo(Integer testNumber) {
        return studentExamMapper.studentExamInfo(testNumber);
    }

    @Override
    public void autoGradeExam(GradingResult gradingResultList) {
        studentExamMapper.getExamResults(gradingResultList);
    }

    @Override
    public int modifyParticipateStatus(Integer participateNumber, Integer paperNumber) {
        return studentExamMapper.modifyParticipateStatus(participateNumber, paperNumber);
    }

}
