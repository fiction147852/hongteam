package com.son.app.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.son.app.exam.service.ExamInfoVO;
import com.son.app.exam.service.ExamListVO;
import com.son.app.exam.service.GradingResult;
import com.son.app.exam.service.ParentChildExamVO;
import com.son.app.exam.service.ParentExamService;
import com.son.app.page.PageVO;

@Controller
public class ParentExamController {
    
	@Autowired
	ParentExamService parentExamService;

	
	/*
	 * @GetMapping("parent/{studentNumber}/exam") public String
	 * childexamListPage(@PathVariable Integer studentNumber, Model model) {
	 * model.addAttribute("studentNumber", studentNumber);
	 * 
	 * return "exam/parent/parent_exam"; }
	 */
	
    @GetMapping("parent/{studentNumber}/{lectureNumber}/exam")
    public String childexamList(@PathVariable Integer studentNumber,@PathVariable Integer lectureNumber, Model model) {
    	List<ParentChildExamVO> list = parentExamService.childexamList(studentNumber, lectureNumber);
    	model.addAttribute("exam", list);
        return "exam/parent/parent_exam";
    }

    @GetMapping("/parent/{studentNumber}/exam/count")
    @ResponseBody
    public int getExamListCount(@PathVariable Integer lectureNumber, @RequestParam(required = false) String testTitle, @RequestParam(required = false) String participateStatus) {
        return parentExamService.examCount(lectureNumber, testTitle, participateStatus);
    }

    @GetMapping("/parent/{studentNumber}/exam/{testNumber}")
    @ResponseBody
    public List<ExamInfoVO> selectExamInfo(@PathVariable int testNumber) {
        return parentExamService.examInfo(testNumber);
    }

    @PostMapping("/parent/{studentNumber}/exam/{participateNumber}/insert")
    @ResponseBody
    public void autoGradeExam(@RequestBody List<GradingResult> gradingResultList, @PathVariable Integer participateNumber) {
        for (GradingResult gradingResult : gradingResultList) {
        	parentExamService.autoGradeExam(gradingResult);
        }
        parentExamService.modifyParticipateStatus(participateNumber);
    }
    
    // 시험 결과 확인
    @GetMapping("parent/{studentNumber}/{lectureNumber}/exam/{testNumber}/result")
    public String viewChildExamResult(@PathVariable Integer studentNumber,
                                      @PathVariable Integer lectureNumber,
                                      @PathVariable Integer testNumber,
                                      @RequestParam Integer participateNumber,
                                      Model model) {
        List<GradingResult> results = parentExamService.getChildExamResults(participateNumber);

        if (!results.isEmpty()) {
            GradingResult firstResult = results.get(0);
            model.addAttribute("paperTitle", firstResult.getPaperTitle());
            model.addAttribute("producer", firstResult.getProducer());
            model.addAttribute("studentName", firstResult.getStudentName());
        }
        model.addAttribute("results", results);
        model.addAttribute("studentNumber", studentNumber);
        model.addAttribute("lectureNumber", lectureNumber);
        model.addAttribute("testNumber", testNumber);
        model.addAttribute("participateNumber", participateNumber);
        
        return "exam/parent/childExamResult";
    }

}
