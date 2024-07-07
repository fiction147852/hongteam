package com.son.app.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.son.app.attendance.service.ParentAttendanceService;
import com.son.app.attendance.service.ParentAttendanceVO;
import com.son.app.security.service.CustomUserDetails;

@Controller
public class ParentAttendanceController {
	
    @Autowired
    ParentAttendanceService parentAttendanceService;
    
	/*
	 * @PostMapping("parent") public String childAttendance(Model
	 * model, @AuthenticationPrincipal CustomUserDetails principal) { int
	 * parentNumber = principal.getMember().getIdNumber(); List<ParentAttendanceVO>
	 * child = parentAttendanceService.selectAttendanceByParentNumber(parentNumber);
	 * 
	 * model.addAttribute("child", child);
	 * 
	 * return "attendance/"; }
	 */
}
