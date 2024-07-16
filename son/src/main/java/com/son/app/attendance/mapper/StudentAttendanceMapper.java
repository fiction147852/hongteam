package com.son.app.attendance.mapper;

import java.util.List;

import com.son.app.attendance.controller.AttendanceSheetVO;
import com.son.app.attendance.service.AttendanceStatsVO;
import com.son.app.attendance.service.StudentLectureInfoVO;
import com.son.app.attendance.service.StudentScheduleDetailVO;
import com.son.app.attendance.service.StudentScheduleVO;
import org.apache.ibatis.annotations.Param;

public interface StudentAttendanceMapper {

    public List<StudentScheduleVO> scheduleList(int studentNumber);
    public List<StudentLectureInfoVO> lectureList(int studentNumber);
    public List<StudentScheduleDetailVO> scheduleDetail(StudentScheduleDetailVO studentScheduleDetailVO);

    public StudentLectureInfoVO lectureInfo(Integer lectureNumber);

    // 출석표
    public List<AttendanceSheetVO> attendanceSchedule(@Param("studentNumber") Integer studentNumber, @Param("lectureNumber") Integer lectureNumber);
    // 출석 상태 계산
    public AttendanceStatsVO studentCalculateAttendanceStats(@Param("lectureNumber") Integer lectureNumber, @Param("studentNumber") Integer studentNumber);
}
