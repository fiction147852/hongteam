package com.son.app.attendance.controller;

import com.son.app.attendance.service.StudentAttendanceService;
import com.son.app.attendance.service.StudentScheduleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StudentAttendanceController {

    @Autowired
    StudentAttendanceService studentAttendanceService;

    // 일정 화면 출력
    @GetMapping("student")
    public String attendancePage() {

        return "attendance/student/attendance";
    }

    @GetMapping("student/{studentNumber}")
    @ResponseBody
    public List<StudentScheduleVO> attendancePage(@PathVariable int studentNumber) {
        List<StudentScheduleVO> studentScheduleVOList = studentAttendanceService.scheduleList(studentNumber);

        return studentScheduleVOList;
    }


}