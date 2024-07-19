package com.son.app.registration.service.impl;

import com.son.app.registration.mapper.StudentCourseRegistrationMapper;
import com.son.app.registration.service.ChargeVO;
import com.son.app.registration.service.CourseRegistrationVO;
import com.son.app.registration.service.StudentCourseRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class studentCourseRegistrationServiceImpl implements StudentCourseRegistrationService {

    @Autowired
    private StudentCourseRegistrationMapper studentCourseRegistrationMapper;


    @Override
    public List<CourseRegistrationVO> courseRegistration(Integer studentNumber, String lectureTitle, String subjectName, int startRow, int endRow) {
        return studentCourseRegistrationMapper.studentCourseRegistration(studentNumber, lectureTitle, subjectName, startRow, endRow);
    }

    @Override
    public int courseRegistrationCount(Integer studentNumber, String lectureTitle, String subjectName) {
        return studentCourseRegistrationMapper.studentCourseRegistrationCount(studentNumber, lectureTitle, subjectName);
    }

    @Override
    public void insertCharge(ChargeVO chargeVO) {
        studentCourseRegistrationMapper.insertCharge(chargeVO);
    }
}
