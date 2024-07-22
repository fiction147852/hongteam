package com.son.app.paper.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	public String paperList(@AuthenticationPrincipal CustomUserDetails userDetails,
							@RequestParam(defaultValue = "1") int page,
							Model model) {
		int instructorNumber = userDetails.getMember().getIdNumber();
		
		PageVO pageVO = paperService.getPageInfo(page);
		List<PaperVO> paperList = paperService.paperList(pageVO);
		List<LectureVO> lectureList = lectureService.getLecturesByInstructor(instructorNumber);
		
		model.addAttribute("lectureList", lectureList);
		model.addAttribute("paperList", paperList);
		model.addAttribute("pageVO", pageVO);
		return "exam/instructor/paperList";
	}
	
	@GetMapping("instructor/paperInfo")
	public String paperInfo(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestParam int paperNumber, Model model) {
		int instructorNumber = userDetails.getMember().getIdNumber();
		List<LectureVO> lectureList = lectureService.getLecturesByInstructor(instructorNumber);
		model.addAttribute("lectureList", lectureList);
		
	    PaperVO paperInfo = paperService.paperInfo(paperNumber);
	    model.addAttribute("paperInfo", paperInfo);
	    return "exam/instructor/paperinfo";
	}
	
	@GetMapping("instructor/paperChoose")
    public String paperInsertChooseForm(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		int instructorNumber = userDetails.getMember().getIdNumber();
		List<LectureVO> lectureList = lectureService.getLecturesByInstructor(instructorNumber);
		model.addAttribute("lectureList", lectureList);
		
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
    									   @AuthenticationPrincipal CustomUserDetails userDetails) {
		int instructorNumber = userDetails.getMember().getIdNumber();
		List<LectureVO> lectureList = lectureService.getLecturesByInstructor(instructorNumber);
		model.addAttribute("lectureList", lectureList);
    	
        List<QuestionVO> questions = paperService.getQuestionsBySubject(subjectCode);
        model.addAttribute("subjectCode", subjectCode);
        model.addAttribute("questions", questions);
        
        InstructorVO instructor = instructorService.getInstructorByUsername(userDetails.getMember().getName());
        model.addAttribute("instructorName", instructor.getName());
        logger.info("Instructor name: {}", instructor.getName());
        
        return "exam/instructor/paperSelectQuestions";
    }

    @PostMapping("instructor/paperSelectQuestions")
    public String paperCreate(@RequestParam String subjectCode,
                              @RequestParam(required = false) List<Integer> selectedQuestions,
                              @RequestParam(required = false) Map<String, String> allRequestParams,
                              @RequestParam String paperTitle,
                              @RequestParam String producer,
                              Model model) throws UnsupportedEncodingException {
        
    	// 문제 미선택 (null 값일 경우)
        if (selectedQuestions == null || selectedQuestions.isEmpty()) {
            return "redirect:paperSelectQuestions?subjectCode=" + subjectCode + "&error=" + URLEncoder.encode("최소 1개 이상의 문제를 선택해주세요.", StandardCharsets.UTF_8);
        }
        
        // 선택문제 20개 초과시
        if (selectedQuestions.size() > 20) {
            return "redirect:paperSelectQuestions?subjectCode=" + subjectCode + "&error=" + URLEncoder.encode("최대 20개의 문제만 선택할 수 있습니다.", StandardCharsets.UTF_8);
        }
        
        // 제목 미입력 (null 값일 경우)
        if (paperTitle == null || paperTitle.trim().isEmpty()) {
            return "redirect:paperSelectQuestions?subjectCode=" + subjectCode + "&error=" + encodedErrorMessageforTitle;
        }

        // 선택된 문제에 해당하는 점수만 필터링
        List<Integer> selectedScores = new ArrayList<>();
        for (Integer questionNumber : selectedQuestions) {
            String scoreKey = "scores[" + questionNumber + "]";
            String scoreValue = allRequestParams.get(scoreKey);
            if (scoreValue != null && !scoreValue.isEmpty()) {
                try {
                    selectedScores.add(Integer.parseInt(scoreValue));
                } catch (NumberFormatException e) {
                    return "redirect:paperSelectQuestions?subjectCode=" + subjectCode + "&error=" + URLEncoder.encode("유효하지 않은 점수 형식입니다.", StandardCharsets.UTF_8);
                }
            } else {
                return "redirect:paperSelectQuestions?subjectCode=" + subjectCode + "&error=" + URLEncoder.encode("선택된 문제의 점수가 입력되지 않았습니다.", StandardCharsets.UTF_8);
            }
        }
        
        // 총 배점 100점 미만
        int totalScore = selectedScores.stream().mapToInt(Integer::intValue).sum();
        if (totalScore != 100) {
            return "redirect:paperSelectQuestions?subjectCode=" + subjectCode + "&error=" + encodedErrorMessageforScore;
        }

        try {
            int paperId = paperService.createPaper(selectedQuestions, selectedScores, paperTitle, producer);
            return "redirect:paperList";
        } catch (Exception e) {
            return "redirect:paperSelectQuestions?subjectCode=" + subjectCode + "&error=" + URLEncoder.encode("시험지 생성 중 오류가 발생했습니다: " + e.getMessage(), StandardCharsets.UTF_8);
        }
    }
    
    
    // 자동생성
    @GetMapping("instructor/paperAutoGenerate")
    public String paperAutoGenerateForm(@RequestParam String subjectCode, Model model, 
    									@AuthenticationPrincipal CustomUserDetails userDetails) {
		int instructorNumber = userDetails.getMember().getIdNumber();
		List<LectureVO> lectureList = lectureService.getLecturesByInstructor(instructorNumber);
		model.addAttribute("lectureList", lectureList);
    	
        model.addAttribute("subjectCode", subjectCode);

        List<QuestionVO> generatedPaper = paperService.generatePaperBySubject(subjectCode);
        model.addAttribute("generatedPaper", generatedPaper);

        if (generatedPaper.size() < 20) {
            model.addAttribute("warningMessage", "요청한 수만큼의 문제를 생성할 수 없었습니다. 현재 생성된 문제 수: " + generatedPaper.size());
        }
        
        InstructorVO instructor = instructorService.getInstructorByUsername(userDetails.getMember().getName());
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
