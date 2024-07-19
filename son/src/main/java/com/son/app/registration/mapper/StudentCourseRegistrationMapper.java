package com.son.app.registration.mapper;

import com.son.app.registration.service.ChargeVO;
import com.son.app.registration.service.CourseRegistrationVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentCourseRegistrationMapper {

    public List<CourseRegistrationVO> studentCourseRegistration(@Param("studentNumber") Integer studentNumber, @Param("lectureTitle") String lectureTitle, @Param("subjectName") String subjectName, @Param("startRow") int startRow, @Param("endRow") int endRow);
    public int studentCourseRegistrationCount(@Param("studentNumber") Integer studentNumber, @Param("lectureTitle") String lectureTitle, @Param("subjectName") String subjectName);

    public int insertCharge(ChargeVO chargeVO);
}
