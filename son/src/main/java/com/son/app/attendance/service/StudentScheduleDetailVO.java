package com.son.app.attendance.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class StudentScheduleDetailVO {
    private Integer studentNumber;

    private String type ;
    private String title;
    private String status;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date postDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadlineDate;
}
