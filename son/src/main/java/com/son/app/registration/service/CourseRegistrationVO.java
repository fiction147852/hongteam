package com.son.app.registration.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CourseRegistrationVO {
    private int rowNum;
    private int lectureCost;
    private Integer lectureNumber;

    private String lectureTitle;
    private String subjectName;
    private String detailSubjectName;
    private String lectureLevelCode;
    private String weekdaysCode;
    private String timeCode;
    private String instructorName;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+9")
    private Date lectureStartDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+9")
    private Date lectureEndDate;
}