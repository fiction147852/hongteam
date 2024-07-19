package com.son.app.registration.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentCourseRegistrationService {

    public List<CourseRegistrationVO> courseRegistration(Integer studentNumber, String lectureTitle, String subjectName, int startRow, int endRow);
    public int courseRegistrationCount(Integer studentNumber, String lectureTitle, String subjectName);

    public void insertCharge(ChargeVO chargeVO);
}
