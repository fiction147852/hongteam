package com.son.app.exam.service;

import lombok.Data;

@Data
public class ParentChildExamVO {
	
    private Integer testNumber;
    private Integer participateNumber;
    private Integer totalScore;
    private String participateStatus; // 응시 여부
    private String testTitle;

    private String examDate;
    
    private Integer lectureNumber;
    private String lectureName;
    private Integer studentNumber;
}
