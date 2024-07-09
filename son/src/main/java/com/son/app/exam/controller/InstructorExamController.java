package com.son.app.exam.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.son.app.exam.service.ExamVO;
import com.son.app.exam.service.InstructorExamService;
import com.son.app.page.PageVO;
import com.son.app.question.service.QuestionVO;

@Controller
public class InstructorExamController {
	
	@Autowired
	InstructorExamService instructorExamService;
	
	@GetMapping("instructor/{lectureNumber}/examList")
	public String examList(@PathVariable Integer lectureNumber,
						   @RequestParam(defaultValue = "1") int page,
						   Model model) {
		PageVO pageVO = instructorExamService.getPageInfo(lectureNumber, page);
		List<ExamVO> examList = instructorExamService.examList(lectureNumber, pageVO);
		
		model.addAttribute("examList", examList);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("lectureNumber", lectureNumber);
		return "exam/instructor/insExamList";
		
	}
	
	// 등록
	@GetMapping("/instructor/{lectureNumber}/examChoose")
	public String examInsertChooseForm(@PathVariable Integer lectureNumber, Model model) {
	model.addAttribute("lectureNumber", lectureNumber);
	return "exam/instructor/insExamInsertChoose";
	}
	
	@PostMapping("/instructor/{lectureNumber}/examChoose")
	public String examInsertChooseProcess(@PathVariable Integer lectureNumber, 
	                                      @RequestParam String type, 
	                                      @RequestParam String subjectCode) {
	    if ("manual".equals(type)) {
	        return "redirect:/instructor/" + lectureNumber + "/examSelectQuestions?subjectCode=" + subjectCode;
	    } else if ("auto".equals(type)) {
	        return "redirect:/instructor/" + lectureNumber + "/examAutoGenerate?subjectCode=" + subjectCode;
	    } else {
	        return "redirect:/instructor/" + lectureNumber + "/examChoose";
	    }
	}
	
	// 선택
	@GetMapping("/instructor/{lectureNumber}/examSelectQuestions")
	public String examSelectQuestionsForm(@PathVariable Integer lectureNumber, 
	                                      @RequestParam String subjectCode, 
	                                      Model model) {
	    List<QuestionVO> questions = instructorExamService.getQuestionsBySubject(subjectCode);
	    model.addAttribute("lectureNumber", lectureNumber);
	    model.addAttribute("subjectCode", subjectCode);
	    model.addAttribute("questions", questions);
	    return "exam/instructor/examSelectQuestions";
	}

	@PostMapping("/instructor/{lectureNumber}/examCreate")
    public String examCreate(@PathVariable Integer lectureNumber,
                             @RequestParam String subjectCode,
                             @RequestParam List<Integer> selectedQuestions,
                             Model model) {
        if (selectedQuestions.size() > 20) {
            model.addAttribute("error", "최대 20개의 문제만 선택할 수 있습니다.");
            return "redirect:/instructor/" + lectureNumber + "/examSelectQuestions?subjectCode=" + subjectCode;
        }

        // 선택된 문제로 시험 생성 로직
        instructorExamService.createExam(lectureNumber, selectedQuestions);

        return "redirect:/instructor/" + lectureNumber + "/examList";
    }
	
	
	// 자동
	@GetMapping("/instructor/{lectureNumber}/examAutoGenerate")
	public String examAutoGenerateForm(@PathVariable Integer lectureNumber, 
	                                   @RequestParam String subjectCode, 
	                                   Model model) {
	    model.addAttribute("lectureNumber", lectureNumber);
	    model.addAttribute("subjectCode", subjectCode);
	    
	    List<QuestionVO> generatedExam = instructorExamService.generateExamBySubject(subjectCode);
	    model.addAttribute("generatedExam", generatedExam);
	    
	    if (generatedExam.size() < 20) {
	        model.addAttribute("warningMessage", "요청한 수만큼의 문제를 생성할 수 없었습니다. 현재 생성된 문제 수: " + generatedExam.size());
	    }
	    
	    return "exam/instructor/examAutoGenerate";
	}
	
	@PostMapping("/instructor/{lectureNumber}/examAutoGenerate")
	public String examAutoGenerate(@PathVariable Integer lectureNumber,
	                               @RequestParam String subjectCode,
	                               @RequestParam String testTitle,
	                               @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date examinationDate,
	                               @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date examDate,
	                               @RequestParam Integer limitTime,
	                               Model model) {
	    int examId = instructorExamService.createAndSaveExam(lectureNumber, subjectCode, testTitle, examinationDate, examDate, limitTime);
	    
	    return "redirect:/instructor/" + lectureNumber + "/examList";
	}
	
}
