package com.son.app.attendance.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class StudentScheduleDetailVO {
    private Integer studentNumber;
    private Integer lectureNumber;

    private String type ;
    private String title;
    private String status;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date postDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadlineDate;
}
