package com.son.app.exam.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ParentChildExamVO {
	
    private int rowNum;
    private Integer testNumber;
    private Integer participateNumber;
    private Integer totalScore;
    private String participateStatus; // 응시 여부
    private String testTitle;
    
    private int limitTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+9")
    private Date examDate;
    
    private Integer lectureNumber;
    private Integer studentNumber;
}
