package com.son.app.attendance.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentAttendanceService {

    public List<StudentScheduleVO> scheduleList(int studentNumber);
    public List<StudentLectureInfoVO> lectureList(int studentNumber);
    public List<StudentScheduleDetailVO> scheduleDetail(StudentScheduleDetailVO studentScheduleDetailVO);

}
