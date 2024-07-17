package com.son.app.exam.service;

import lombok.Data;

@Data
public class GradingResult {

    private Integer paperNumber;
    private Integer participateNumber;
    private Integer questionNumber;
    private String studentAnswer;
    
    // 결과 확인용
    private Integer gradingResultNumber;
    private String answerStatus;
    private String correctAnswer;  // 문제의 정답
    private String questionText;   // 문제 내용
    private Integer score;         // 문제 배점
    private String producer;
    private String paperTitle;
    private String studentName;
}
