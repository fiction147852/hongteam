package com.son.app.lecture.service;

import lombok.Data;

@Data
public class MainLectureVO {
	private Integer lectureNumber;
    private String lectureTitle;
    private String lectureDescription;
    private int lectureCost;
    private String subjectName;
    private String detailSubjectName;
    private int capacity;
    private String lectureLevelCode;
    private String weekdaysCode;
    private String timeCode;
    private String lectureStartDate;
    private String lectureEndDate;
    private String instructorName;
}
