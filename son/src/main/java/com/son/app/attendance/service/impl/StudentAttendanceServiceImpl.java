package com.son.app.attendance.service.impl;

import com.son.app.attendance.mapper.StudentAttendanceMapper;
import com.son.app.attendance.service.StudentAttendanceService;
import com.son.app.attendance.service.StudentLectureInfoVO;
import com.son.app.attendance.service.StudentScheduleDetailVO;
import com.son.app.attendance.service.StudentScheduleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentAttendanceServiceImpl implements StudentAttendanceService {
    // 필드 주입 (변경할 예정)
    @Autowired
    StudentAttendanceMapper studentAttendanceMapper;

    @Override
    public List<StudentScheduleVO> scheduleList(int studentNumber) {
        return studentAttendanceMapper.scheduleList(studentNumber);
    }

    @Override
    public List<StudentLectureInfoVO> lectureList(int studentNumber) {
        return studentAttendanceMapper.lectureList(studentNumber);
    }

    @Override
    public List<StudentScheduleDetailVO> scheduleDetail(StudentScheduleDetailVO studentScheduleDetailVO) {
        return studentAttendanceMapper.scheduleDetail(studentScheduleDetailVO);
    }
}
