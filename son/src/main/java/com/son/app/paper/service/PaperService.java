package com.son.app.paper.service;

import java.util.List;

import com.son.app.page.PageVO;
import com.son.app.question.service.QuestionVO;

public interface PaperService {
	public List<PaperVO> paperList(PageVO pageVO);
	public PageVO getPageInfo(int page);
	
	public PaperVO paperInfo(int paperNumber);
	
	List<QuestionVO> getQuestionsBySubject(String subjectCode);
    List<QuestionVO> generatePaperBySubject(String subjectCode);
    int createPaper(List<Integer> questionNumbers, List<Integer> scores, String paperTitle, String producer);
}
