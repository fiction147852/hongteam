package com.son.app.lecture.controller;

import com.son.app.lecture.service.LectureMaterialDetailVO;
import com.son.app.lecture.service.LectureMaterialVO;
import com.son.app.lecture.service.StudentLectureService;
import com.son.app.security.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentLectureController {

    @Autowired
    private StudentLectureService studentLectureService;

    @GetMapping("student/{lectureNumber}/lectureMaterials")
    public String detailSubjecAttendancePage(@AuthenticationPrincipal CustomUserDetails principal, @PathVariable Integer lectureNumber, Model model) {
        model.addAttribute("lectureNumber", lectureNumber);

        return "lecture/student/lectureMaterials";
    }

    @GetMapping("/student/{lectureNumber}/lectureMaterials/list")
    @ResponseBody
    public List<LectureMaterialVO> getLectureMaterials(@PathVariable Integer lectureNumber, @RequestParam(required = false) String title, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize) {
        int startRow = (page - 1) * pageSize + 1;
        int endRow = page * pageSize;
        return studentLectureService.lectureMaterialList(lectureNumber, title, startRow, endRow);
    }

    @GetMapping("/student/{lectureNumber}/lectureMaterials/count")
    @ResponseBody
    public int getLectureMaterialsCount(@PathVariable Integer lectureNumber, @RequestParam(required = false) String title) {
        return studentLectureService.lectureMaterialCount(lectureNumber, title);
    }

    @GetMapping("/student/{lectureNumber}/lectureMaterials/{lectureMaterialNumber}")
    @ResponseBody
    public LectureMaterialDetailVO lectureMaterialInfo(@PathVariable Integer lectureMaterialNumber) {
        return studentLectureService.lectureMaterialInfo(lectureMaterialNumber);
    }

}
