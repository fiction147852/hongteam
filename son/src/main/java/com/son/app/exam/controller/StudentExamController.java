package com.son.app.exam.controller;

import com.son.app.exam.service.ExamInfoVO;
import com.son.app.exam.service.ExamListVO;
import com.son.app.exam.service.GradingResult;
import com.son.app.exam.service.StudentExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@Controller
public class StudentExamController {

    @Autowired
    private StudentExamService studentExamService;

    @GetMapping("student/{lectureNumber}/exam")
    public String examListPage(@PathVariable Integer lectureNumber, Model model) {
        model.addAttribute("lectureNumber", lectureNumber);

        return "exam/student/examList";
    }

    @GetMapping("student/{lectureNumber}/exam/list")
    @ResponseBody
    public List<ExamListVO> examList(@PathVariable Integer lectureNumber, @RequestParam(required = false) String testTitle, @RequestParam(required = false) String participateStatus, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize) {
        int startRow = (page - 1) * pageSize + 1;
        int endRow = page * 5;

        return studentExamService.examList(lectureNumber, testTitle, participateStatus, startRow, endRow);
    }

    @GetMapping("/student/{lectureNumber}/exam/count")
    @ResponseBody
    public int getExamListCount(@PathVariable Integer lectureNumber, @RequestParam(required = false) String testTitle, @RequestParam(required = false) String participateStatus) {
        return studentExamService.examCount(lectureNumber, testTitle, participateStatus);
    }

    @GetMapping("/student/{lectureNumber}/exam/{testNumber}")
    @ResponseBody
    public List<ExamInfoVO> selectExamInfo(@PathVariable int testNumber) {
        return studentExamService.examInfo(testNumber);
    }

    @PostMapping("/student/{lectureNumber}/exam/{participateNumber}/insert")
    @ResponseBody
    public void autoGradeExam(@RequestBody List<GradingResult> gradingResultList, @PathVariable Integer participateNumber) {
        for (GradingResult gradingResult : gradingResultList) {
            studentExamService.autoGradeExam(gradingResult);
        }
        studentExamService.modifyParticipateStatus(participateNumber);
    }



}
