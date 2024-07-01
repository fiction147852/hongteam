package com.son.app.lecture.controller;

import com.son.app.security.service.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentLectureController {


    @GetMapping("student/{detailSubjectName}/lectureMaterials")
    public String detailSubjecAttendancePage(@AuthenticationPrincipal CustomUserDetails principal, @PathVariable String detailSubjectName, @RequestParam Integer lectureNumber, Model model) {

        model.addAttribute("lectureNumber", lectureNumber);

        return "lecture/student/lectureMaterials";
    }



}
