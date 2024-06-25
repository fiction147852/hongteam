package com.son.app.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class instructorController {

    @GetMapping("instructor")
    public String instructorAttendancePage(Model model) {
        return "attendance/instructor/insAttendance";
    }

}