package com.son.app.exam.service.impl;

import com.son.app.exam.mapper.StudentExamMapper;
import com.son.app.exam.service.ExamListVO;
import com.son.app.exam.service.StudentExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentExamServiceImpl implements StudentExamService {

    @Autowired
    private StudentExamMapper studentExamMapper;

    @Override
    public List<ExamListVO> examList(Integer lectureNumber, String testTitle, String participateStatus , int startRow, int endRow) {
        return studentExamMapper.studentExamList(lectureNumber, testTitle, participateStatus, startRow, endRow);
    }

    @Override
    public int examCount(Integer lectureNumber, String testTitle, String participateStatus) {
        return studentExamMapper.studentExamCount(lectureNumber, testTitle, participateStatus);
    }
}
