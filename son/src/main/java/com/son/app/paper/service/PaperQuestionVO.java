package com.son.app.paper.service;

import com.son.app.question.service.QuestionVO;

import lombok.Data;

@Data
public class PaperQuestionVO extends QuestionVO {
	private Integer score;
}
