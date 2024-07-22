package com.son.app.attendance.controller;

import com.son.app.attendance.service.*;
import com.son.app.security.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StudentAttendanceController {

    @Autowired
    StudentAttendanceService studentAttendanceService;

    // 일정 화면 출력
    @GetMapping("student")
    public String attendancePage(Model model, @AuthenticationPrincipal CustomUserDetails principal, HttpSession session) {
        int studentNumber = principal.getMember().getIdNumber();
        List<StudentLectureInfoVO> studentLectureInfoVOS = studentAttendanceService.lectureList(studentNumber);
        session.setAttribute("lectureList", studentLectureInfoVOS);
        model.addAttribute("lectureList", studentLectureInfoVOS);

        return "attendance/student/attendance";
    }

    // 일정 정보 받기
    @GetMapping("student/schedule")
    @ResponseBody
    public List<StudentScheduleVO> attendancePage(@AuthenticationPrincipal CustomUserDetails principal) {
        int studentNumber = principal.getMember().getIdNumber();
        List<StudentScheduleVO> studentScheduleVOList = studentAttendanceService.scheduleList(studentNumber);

        return studentScheduleVOList;
    }

    // 과제 및 시험 상세 정보
    @GetMapping("student/scheduleDetail")
    @ResponseBody
    public List<StudentScheduleDetailVO> scheduleDetail(@AuthenticationPrincipal CustomUserDetails principal,  StudentScheduleDetailVO studentScheduleDetailVO) {
        int studentNumber = principal.getMember().getIdNumber();
        studentScheduleDetailVO.setStudentNumber(studentNumber);

        return studentAttendanceService.scheduleDetail(studentScheduleDetailVO);
    }
    // ----------------------------------------------------------------------------------------------------------------------------------------------------------

    // 강의별 출석 화면
    @GetMapping("student/{lectureNumber}")
    public String detailSubjecAttendancePage(@AuthenticationPrincipal CustomUserDetails principal, @PathVariable Integer lectureNumber, HttpSession httpSession,  Model model) {
        int studentNumber = principal.getMember().getIdNumber();

        StudentLectureInfoVO studentLectureInfoVO = studentAttendanceService.lectureInfo(lectureNumber);
        httpSession.setAttribute("studentLectureInfo", studentLectureInfoVO);

        return "attendance/student/subjectDetailAttendance";
    }

    // 출석표 (상태)
    @GetMapping("student/{lectureNumber}/attendanceStatus")
    @ResponseBody
    public List<AttendanceSheetVO> attendanceSheetList(@AuthenticationPrincipal CustomUserDetails principal, @PathVariable Integer lectureNumber) {
        int studentNumber = principal.getMember().getIdNumber();

        return studentAttendanceService.attendanceSchedule(studentNumber, lectureNumber);
    }

    // 출석 상태
    @GetMapping("student/{lectureNumber}/attendanceStatusCount")
    @ResponseBody
    public AttendanceStatsVO calculateAttendanceStats(@AuthenticationPrincipal CustomUserDetails principal, @PathVariable Integer lectureNumber) {
        int studentNumber = principal.getMember().getIdNumber();

        AttendanceStatsVO stats = new AttendanceStatsVO();
        stats.setTotalDays(0);
        stats.setAttendanceDays(0);
        stats.setTardyDays(0);
        stats.setEarlyLeaveDays(0);
        stats.setAbsentDays(0);

        return stats;
    }
}