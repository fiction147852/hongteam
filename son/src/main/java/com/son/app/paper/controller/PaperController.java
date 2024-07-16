package com.son.app.paper.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.son.app.lecture.service.InsLectureService;
import com.son.app.lecture.service.LectureVO;
import com.son.app.member.service.InstructorService;
import com.son.app.member.service.InstructorVO;
import com.son.app.page.PageVO;
import com.son.app.paper.service.PaperService;
import com.son.app.paper.service.PaperVO;
import com.son.app.question.service.QuestionVO;
import com.son.app.security.service.CustomUserDetails;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PaperController {
	@Autowired
	PaperService paperService;
	
	@Autowired
	InsLectureService lectureService;
	
	@Autowired
	InstructorService instructorService;
	
	private static final Logger logger = LoggerFactory.getLogger(PaperController.class);
	String encodedErrorMessageforScore = URLEncoder.encode("총 배점은 정확히 100점이어야 합니다.", StandardCharsets.UTF_8);
	String encodedErrorMessageforTitle = URLEncoder.encode("시험지 제목을 입력해주세요.", StandardCharsets.UTF_8);
	
	@GetMapping("instructor/paperList")
	public String paperList(@RequestParam(defaultValue = "1") int page,
							Model model) {
		PageVO pageVO = paperService.getPageInfo(page);
		List<PaperVO> paperList = paperService.paperList(pageVO);
		List<LectureVO> lecturelist = lectureService.lectureList();
		
		model.addAttribute("lectureList", lecturelist);
		model.addAttribute("paperList", paperList);
		model.addAttribute("pageVO", pageVO);
		return "exam/instructor/paperList";
	}
	
	@GetMapping("instructor/paperInfo")
	public String paperInfo(@RequestParam int paperNumber, Model model) {
	    PaperVO paperInfo = paperService.paperInfo(paperNumber);
	    model.addAttribute("paperInfo", paperInfo);
	    return "exam/instructor/paperinfo";
	}
	
	@GetMapping("instructor/paperChoose")
    public String paperInsertChooseForm(Model model) {
        return "exam/instructor/paperChoose";
    }
	
	// 유형선택
	@PostMapping("/instructor/paperChoose")
	public String paperInsertChooseProcess(@RequestParam String type,
	                                       @RequestParam String subjectCode) {
	    if ("manual".equals(type)) {
	        return "redirect:paperSelectQuestions?subjectCode=" + subjectCode;
	    } else if ("auto".equals(type)) {
	        return "redirect:paperAutoGenerate?subjectCode=" + subjectCode;
	    } else {
	        return "redirect:paperChoose";
	    }
	}
    
    // 문제선택
    @GetMapping("instructor/paperSelectQuestions")
    public String paperSelectQuestionsForm(@RequestParam String subjectCode, Model model, 
    									   @AuthenticationPrincipal CustomUserDetails principal) {
        List<QuestionVO> questions = paperService.getQuestionsBySubject(subjectCode);
        model.addAttribute("subjectCode", subjectCode);
        model.addAttribute("questions", questions);
        
        InstructorVO instructor = instructorService.getInstructorByUsername(principal.getMember().getName());
        model.addAttribute("instructorName", instructor.getName());
        logger.info("Instructor name: {}", instructor.getName());
        
        return "exam/instructor/paperSelectQuestions";
    }

    @PostMapping("instructor/paperSelectQuestions")
    public String paperCreate(@RequestParam String subjectCode,
                              @RequestParam List<Integer> selectedQuestions,
                              @RequestParam List<Integer> scores,
                              @RequestParam String paperTitle,
                              @RequestParam String producer,
                              Model model) {
        if (selectedQuestions.size() > 20) {
            model.addAttribute("error", "최대 20개의 문제만 선택할 수 있습니다.");
            return "redirect:paperSelectQuestions?subjectCode=" + subjectCode;
        }
        
        if (paperTitle == null || paperTitle.trim().isEmpty()) {
            return "redirect:paperSelectQuestions?subjectCode=" + subjectCode + "&error=" + encodedErrorMessageforTitle;
        }

        int totalScore = scores.stream().mapToInt(Integer::intValue).sum();
        if (totalScore != 100) {
            model.addAttribute("error", "총 배점은 정확히 100점이어야 합니다.");
            return "redirect:paperSelectQuestions?subjectCode=" + subjectCode + "&error=" + encodedErrorMessageforScore;
        }

        int paperId = paperService.createPaper(selectedQuestions, scores, paperTitle, producer);

        return "redirect:paperList";
    }
    
    
    // 자동생성
    @GetMapping("instructor/paperAutoGenerate")
    public String paperAutoGenerateForm(@RequestParam String subjectCode, Model model, 
    									@AuthenticationPrincipal CustomUserDetails principal) {
        model.addAttribute("subjectCode", subjectCode);

        List<QuestionVO> generatedPaper = paperService.generatePaperBySubject(subjectCode);
        model.addAttribute("generatedPaper", generatedPaper);

        if (generatedPaper.size() < 20) {
            model.addAttribute("warningMessage", "요청한 수만큼의 문제를 생성할 수 없었습니다. 현재 생성된 문제 수: " + generatedPaper.size());
        }
        
        InstructorVO instructor = instructorService.getInstructorByUsername(principal.getMember().getName());
        model.addAttribute("instructorName", instructor.getName());

        return "exam/instructor/paperAutoGenerate";
    }

    @PostMapping("instructor/paperAutoGenerate")
    public String paperAutoCreate(@RequestParam String subjectCode,
                                  @RequestParam List<Integer> questionNumbers,
                                  @RequestParam List<Integer> scores,
                                  @RequestParam String paperTitle,
                                  @RequestParam String producer,
                                  Model model) {
    	log.info("paperTitle: {}, producer: {}", paperTitle, producer);
    	// paperTitle이 비어있는지 검사
        if (paperTitle == null || paperTitle.trim().isEmpty()) {
            return "redirect:paperAutoGenerate?subjectCode=" + subjectCode + "&error=" + encodedErrorMessageforTitle;
        }
        
        int totalScore = scores.stream().mapToInt(Integer::intValue).sum();
        if (totalScore != 100) {
            model.addAttribute("error", "총 배점은 정확히 100점이어야 합니다.");
            return "redirect:paperAutoGenerate?subjectCode=" + subjectCode + "&error=" + encodedErrorMessageforScore;
        }	

        int paperId = paperService.createPaper(questionNumbers, scores, paperTitle, producer);

        return "redirect:paperList";
    }
	
    @PostMapping("/instructor/deletePaper")
    public String deletePaper(@RequestParam int paperNumber, RedirectAttributes redirectAttributes) {
        try {
            paperService.deletePaper(paperNumber);
            redirectAttributes.addFlashAttribute("alertType", "success");
            redirectAttributes.addFlashAttribute("message", "시험지가 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("alertType", "error");
            redirectAttributes.addFlashAttribute("message", "시험지 삭제 중 오류가 발생했습니다.");
        }
        return "redirect:/instructor/paperList";
    }
}
