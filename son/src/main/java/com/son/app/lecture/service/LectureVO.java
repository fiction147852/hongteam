package com.son.app.lecture.service;

import java.util.Date;

import lombok.Data;

@Data
public class LectureVO {
    private Integer lectureNumber;
    private String lectureTitle;
    private String lectureDescription;
    private int lectureCost;
    private String subjectCode;
    private String detailSubjectCode;
    private int capacity;
    private String lectureLevelCode;
    private String weekdaysCode;
    private String timeCode;
    private Date lectureStartDate;
    private Date lectureEndDate;
    private Integer instructorNumber;
}
