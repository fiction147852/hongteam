package com.son.app.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.son.app.exam.mapper.InstructorExamMapper;
import com.son.app.exam.service.ExamVO;
import com.son.app.exam.service.GradingResult;
import com.son.app.exam.service.InstructorExamService;
import com.son.app.lecture.service.InsLectureService;
import com.son.app.lecture.service.LectureVO;
import com.son.app.page.PageVO;
import com.son.app.paper.service.PaperService;
import com.son.app.paper.service.PaperVO;

@Controller
public class InstructorExamController {
	
	@Autowired
	InstructorExamService instructorExamService;
	
	@Autowired
	PaperService paperService;
	
	@Autowired
	InsLectureService lectureService;
	
	@Autowired
	InstructorExamMapper examMapper;
	
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
	
    @GetMapping("instructor/{lectureNumber}/exam/{testNumber}/completedStudents")
    public String completedStudentList(@PathVariable Integer lectureNumber,
                                       @PathVariable Integer testNumber,
                                       @RequestParam(defaultValue = "1") int page,
                                       Model model) {
        PageVO pageVO = instructorExamService.getCompletedStudentPageInfo(testNumber, page);
        List<ExamVO> completedStudents = instructorExamService.getCompletedStudentList(testNumber, pageVO);

        model.addAttribute("completedStudents", completedStudents);
        model.addAttribute("pageVO", pageVO);
        model.addAttribute("lectureNumber", lectureNumber);
        model.addAttribute("testNumber", testNumber);
        return "exam/instructor/insCompletedStudentList";
    }
    
    @GetMapping("instructor/{lectureNumber}/exam/{testNumber}/studentResult")
    public String viewStudentExamResult(@PathVariable Integer lectureNumber,
                                        @PathVariable Integer testNumber,
                                        @RequestParam Integer participateNumber,
                                        Model model) {
        List<GradingResult> results = examMapper.getStudentExamResults(participateNumber);
        
        if (!results.isEmpty()) {
            GradingResult firstResult = results.get(0);
            model.addAttribute("paperTitle", firstResult.getPaperTitle());
            model.addAttribute("producer", firstResult.getProducer());
            model.addAttribute("studentName", firstResult.getStudentName());
        }
        model.addAttribute("results", results);
        model.addAttribute("lectureNumber", lectureNumber);
        model.addAttribute("testNumber", testNumber);
        model.addAttribute("participateNumber", participateNumber);
        return "exam/instructor/examResult";
    }
	
	@GetMapping("instructor/createExam")
	public String showCreateExamForm(@RequestParam Integer paperNumber, Model model) {
	    // paperNumber를 이용해 시험지 정보를 가져옵니다
	    PaperVO paper = paperService.getPaperByNumber(paperNumber);
	    model.addAttribute("paper", paper);
	    
	    // 강의 목록을 가져와 모델에 추가합니다
	    List<LectureVO> lectures = lectureService.lectureList();
	    model.addAttribute("lectures", lectures);
	    
	    return "exam/instructor/createExamForm";
	}
	
	@PostMapping("instructor/createExam")
	public String createExam(@ModelAttribute ExamVO exam, RedirectAttributes redirectAttributes) {
	    // 해당 강의를 수강하는 학생들의 목록을 가져옵니다.
	    List<Integer> studentNumbers = lectureService.getStudentNumbersByLecture(exam.getLectureNumber());
	    
	    if (studentNumbers.isEmpty()) {
	        redirectAttributes.addFlashAttribute("swalMessage", "강의중인 학생이 없습니다.");
	        return "redirect:/instructor/createExam?paperNumber=" + exam.getPaperNumber();
	    }
	    
	    instructorExamService.createExamWithParticipation(exam, studentNumbers);

	    return "redirect:/instructor/" + exam.getLectureNumber() + "/examList";
	}
	
}
