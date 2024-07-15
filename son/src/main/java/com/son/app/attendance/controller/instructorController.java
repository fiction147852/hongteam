package com.son.app.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.son.app.lecture.service.InsLectureService;
import com.son.app.lecture.service.LectureVO;

@Controller
public class instructorController {
	@Autowired
	InsLectureService lectureService;

    @GetMapping("instructor")
    public String instructorAttendancePage(Model model) {
    	
    	List<LectureVO> lecturelist = lectureService.lectureList();
		
		model.addAttribute("lectureList", lecturelist);
        return "attendance/instructor/insAttendance";
    }
    
    
    

}