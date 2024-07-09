package com.son.app.paper.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.son.app.page.PageVO;
import com.son.app.paper.controller.PaperController;
import com.son.app.paper.mapper.PaperMapper;
import com.son.app.paper.service.PaperService;
import com.son.app.paper.service.PaperVO;
import com.son.app.question.service.QuestionVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaperServiceImpl implements PaperService {
	private static final Logger logger = LoggerFactory.getLogger(PaperController.class);
	@Autowired
	PaperMapper paperMapper;

	@Override
	public List<PaperVO> paperList(PageVO pageVO) {
		int start = (pageVO.getPage() - 1 ) * pageVO.getPageSize() + 1;
		int end = start + pageVO.getPageSize() - 1;
		return paperMapper.selectPaperAll(start, end);
	}
	
	@Override
	public PageVO getPageInfo(int page) {
		int totalItems = paperMapper.countPaper();
		return new PageVO(page, totalItems, 5, 5);
	}

	@Override
	public PaperVO paperInfo(int paperNumber) {
	    return paperMapper.selectPaperInfo(paperNumber);
	}
	
	@Override
    public List<QuestionVO> getQuestionsBySubject(String subjectCode) {
        return paperMapper.selectQuestionsBySubject(subjectCode);
    }

    @Override
    public List<QuestionVO> generatePaperBySubject(String subjectCode) {
        List<QuestionVO> paper = new ArrayList<>();

        Map<String, Integer> difficultyCount = Map.of(
            "상", 5,
            "중", 10,
            "하", 5
        );

        for (Map.Entry<String, Integer> entry : difficultyCount.entrySet()) {
            String difficulty = entry.getKey();
            int count = entry.getValue();

            List<QuestionVO> questions = paperMapper.selectRandomQuestionsBySubjectAndDifficulty(subjectCode, difficulty, count);
            paper.addAll(questions);
        }

        return paper;
    }

    @Override
    public int createPaper(List<Integer> questionNumbers, List<Integer> scores, String paperTitle, String producer) {
        PaperVO paper = new PaperVO();
        paper.setPaperTitle(paperTitle);
        paper.setProducer(producer);

        paperMapper.insertPaper(paper);
        int paperNumber = paper.getPaperNumber();
        logger.info("Created paper with number: {}", paperNumber);

        for (int i = 0; i < questionNumbers.size(); i++) {
        	logger.info("Inserting question {} with score {} for paper {}", questionNumbers.get(i), scores.get(i), paperNumber);
            paperMapper.insertPaperQuestion(paperNumber, questionNumbers.get(i), scores.get(i));
        }

        return paperNumber;
    }
    
    @Override
    @Transactional
    public void deletePaper(int paperNumber) {
        paperMapper.deletePaperContent(paperNumber);
        paperMapper.deletePaper(paperNumber);
    }
    
    @Override
    public PaperVO getPaperByNumber(int paperNumber) {
        return paperInfo(paperNumber);  // 기존의 paperInfo 메소드를 활용
    }
}
