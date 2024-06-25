package com.son.app.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {

    @GetMapping("student")
    public String attendancePage(Model model) {
        return "attendance/student/attendance";
    }

}