package com.son.app.question.service;

import lombok.Data;

@Data
public class QuestionVO {
	private Integer questionNumber;
	private String questionTypeCode;
	private String text;
	private String attachmentFile;
	private String subjectCode;
	private String detailSubjectCode;
	private String subjectName;
	private String detailSubjectName;
	private String difficultyCode;
	private String answer;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String option5;
	private int instructorNumber;
	private String instructorName;
	private String explanation;
	
}
