package com.son.app.attendance.mapper;

import java.util.List;

import com.son.app.attendance.service.StudentLectureInfoVO;
import com.son.app.attendance.service.StudentScheduleDetailVO;
import com.son.app.attendance.service.StudentScheduleVO;

public interface StudentAttendanceMapper {

    public List<StudentScheduleVO> scheduleList(int studentNumber);
    public List<StudentLectureInfoVO> lectureList(int studentNumber);
    public List<StudentScheduleDetailVO> scheduleDetail(StudentScheduleDetailVO studentScheduleDetailVO);
}
