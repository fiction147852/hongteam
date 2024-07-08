package com.son.app.paper.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	private static final Logger logger = LoggerFactory.getLogger(PaperController.class);
	@Autowired
	PaperService paperService;
	
	@Autowired
	InstructorService instructorService;
	
	@GetMapping("instructor/paperList")
	public String paperList(@RequestParam(defaultValue = "1") int page,
							Model model) {
		PageVO pageVO = paperService.getPageInfo(page);
		List<PaperVO> paperList = paperService.paperList(pageVO);
		
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

        int totalScore = scores.stream().mapToInt(Integer::intValue).sum();
        if (totalScore > 100) {
            model.addAttribute("error", "총 배점은 100점을 초과할 수 없습니다.");
            return "redirect:paperSelectQuestions?subjectCode=" + subjectCode;
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
        int totalScore = scores.stream().mapToInt(Integer::intValue).sum();
        if (totalScore > 100) {
            model.addAttribute("error", "총 배점은 100점을 초과할 수 없습니다.");
            return "redirect:autoGenerate?subjectCode=" + subjectCode;
        }

        int paperId = paperService.createPaper(questionNumbers, scores, paperTitle, producer);

        return "redirect:paperList";
    }
	
}
