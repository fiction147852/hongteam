package com.son.app.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.son.app.lecture.service.InsLectureService;
import com.son.app.lecture.service.LectureVO;
import com.son.app.security.service.CustomUserDetails;

@Controller
public class instructorController {
	@Autowired
	InsLectureService lectureService;

    @GetMapping("instructor")
    public String instructorAttendancePage(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        int instructorNumber = userDetails.getMember().getIdNumber();
        List<LectureVO> lectureList = lectureService.getLecturesByInstructor(instructorNumber);
        
        model.addAttribute("lectureList", lectureList);
        return "attendance/instructor/insAttendance";
    }
    
    @GetMapping("instructor/404")
    public String notFoundErrorPage() {
    	return "pages/samples/error-404-ins";
    }

}