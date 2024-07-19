package com.son.app.registration.controller;

import com.son.app.registration.service.ChargeVO;
import com.son.app.registration.service.CourseRegistrationVO;
import com.son.app.registration.service.StudentCourseRegistrationService;
import com.son.app.security.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentCourseRegistrationController {

    @Autowired
    private StudentCourseRegistrationService studentCourseRegistrationService;

    @GetMapping("student/courseRegistration")
    public String courseRegistrationListPage(@AuthenticationPrincipal CustomUserDetails principal, Model model) {
        int studentNumber = principal.getMember().getIdNumber();

        model.addAttribute("studentNumber", studentNumber);

        return "lecture/student/lectureApplication";
    }

    @GetMapping("student/courseRegistration/list")
    @ResponseBody
    public List<CourseRegistrationVO> courseRegistrationList(@AuthenticationPrincipal CustomUserDetails principal, @RequestParam(required = false) String lectureTitle, @RequestParam(required = false) String subjectName, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "6") int pageSize) {
        int studentNumber = principal.getMember().getIdNumber();
        int startRow = (page - 1) * pageSize + 1;
        int endRow = page * 6;

        return studentCourseRegistrationService.courseRegistration(studentNumber, lectureTitle, subjectName, startRow, endRow);
    }

    @GetMapping("student/courseRegistration/count")
    @ResponseBody
    public int courseRegistrationListCount(@AuthenticationPrincipal CustomUserDetails principal, @RequestParam(required = false) String lectureTitle, @RequestParam(required = false) String subjectName) {
        int studentNumber = principal.getMember().getIdNumber();

        return studentCourseRegistrationService.courseRegistrationCount(studentNumber, lectureTitle, subjectName);
    }

    @PostMapping("student/courseRegistration/insert")
    @ResponseBody
    public ResponseEntity<Void> insertCharge(@RequestBody List<ChargeVO> chargeVOList) {
        for (ChargeVO chargeVO : chargeVOList) {
            studentCourseRegistrationService.insertCharge(chargeVO);
        }
        return ResponseEntity.ok().build();
    }

}
