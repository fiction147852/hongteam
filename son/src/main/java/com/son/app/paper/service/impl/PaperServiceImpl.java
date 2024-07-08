package com.son.app.paper.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.page.PageVO;
import com.son.app.paper.mapper.PaperMapper;
import com.son.app.paper.service.PaperService;
import com.son.app.paper.service.PaperVO;
import com.son.app.question.service.QuestionVO;

@Service
public class PaperServiceImpl implements PaperService {
	
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
        // 기타 필요한 정보 설정

        int paperNumber = paperMapper.insertPaper(paper);

        for (int i = 0; i < questionNumbers.size(); i++) {
            paperMapper.insertPaperQuestion(paperNumber, questionNumbers.get(i), scores.get(i));
        }

        return paperNumber;
    }
}
