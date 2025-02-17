package com.son.app.exam.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Data
public class ExamVO {

    private Integer testNumber;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date examinationDate;
    private Integer limitTime; //Duration 사용시 핸들러나 컨버터를 구현
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date examDate;
    
    private Integer paperNumber;
    private Integer lectureNumber;
    private String testTitle;
    
    // 응시완료자
    private Integer studentNumber;
    private String studentName;
    private Integer participateNumber;
    private String participateStatus;
    private Integer totalScore;
    private String feedback;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date participateDate;
    
    private Integer completedCount;
    
}
