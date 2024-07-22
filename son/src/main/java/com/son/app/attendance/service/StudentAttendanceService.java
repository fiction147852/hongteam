package com.son.app.attendance.service;

import com.son.app.attendance.controller.AttendanceSheetVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentAttendanceService {

    public List<StudentScheduleVO> scheduleList(int studentNumber);
    public List<StudentLectureInfoVO> lectureList(int studentNumber);
    public List<StudentScheduleDetailVO> scheduleDetail(StudentScheduleDetailVO studentScheduleDetailVO);
    public StudentLectureInfoVO lectureInfo(Integer lectureNumber);

    public List<AttendanceSheetVO> attendanceSchedule(Integer studentNumber, Integer lectureNumber);
//    public AttendanceStatsVO calculateAttendanceStats(Integer lectureNumber, Integer studentNumber);

}
