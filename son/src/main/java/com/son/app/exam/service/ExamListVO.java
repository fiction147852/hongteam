package com.son.app.exam.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ExamListVO {

    private int rowNum;
    private Integer participateNumber;
    private Integer total_score;
    private String participate_status;
    private String testTitle;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date examDate;
}
