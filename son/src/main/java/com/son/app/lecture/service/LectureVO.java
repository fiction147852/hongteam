package com.son.app.lecture.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class LectureVO {
    private Integer lectureNumber;
    private String lectureTitle;
    private String lectureDescription;
    private int lectureCost;
    private String subjectName;
    private String detailSubjectName;
    private String subjectCode;
    private String detailSubjectCode;
    private int capacity;
    private String lectureLevelCode;
    private String weekdaysCode;
    private String timeCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lectureStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lectureEndDate;
    private Integer instructorNumber;
    
    private String instructorName;
    
    // 학생
    private Integer studentNumber;
    private String studentName;
    private String email;
    private String phone;
}
