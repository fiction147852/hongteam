package com.son.app.task.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class TaskListVO {
    private int rowNum;
    private Integer taskNumber;
    private String title;
    private String description;
    private String taskSubmitStatus;
    private String feedbackStatus;
    private String feedback;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date postDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date submitDeadline;
}
