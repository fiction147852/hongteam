package com.son.app.attendance.controller;

import com.son.app.attendance.service.StudentAttendanceService;
import com.son.app.attendance.service.StudentLectureInfoVO;
import com.son.app.attendance.service.StudentScheduleDetailVO;
import com.son.app.attendance.service.StudentScheduleVO;
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
    public String attendancePage(Model model, @AuthenticationPrincipal CustomUserDetails principal) {
        int studentNumber = principal.getMember().getIdNumber();
        List<StudentLectureInfoVO> studentLectureInfoVOS = studentAttendanceService.lectureList(studentNumber);

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




}