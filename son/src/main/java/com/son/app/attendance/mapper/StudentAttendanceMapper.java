package com.son.app.attendance.mapper;

import com.son.app.attendance.service.StudentLectureInfoVO;
import com.son.app.attendance.service.StudentScheduleVO;

import java.util.List;

public interface StudentAttendanceMapper {

    public List<StudentScheduleVO> scheduleList(int studentNumber);
    public List<StudentLectureInfoVO> lectureList(int studentNumber);
}
