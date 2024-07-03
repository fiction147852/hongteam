package com.son.app.exam.controller;

import com.son.app.security.service.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StudentExamController {

    @GetMapping("student/{lectureNumber}/exam")
    public String examListPage(@AuthenticationPrincipal CustomUserDetails principal, @PathVariable Integer lectureNumber, Model model) {
        model.addAttribute("lectureNumber", lectureNumber);

        return "exam/student/examList";
    }






}
