package com.son.app.lecture.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class LectureMaterialVO {

    private Integer lectureMaterialNumber; // 기본 키
    private Integer lectureNumber;
    private String title;
    private String content;
    private int views;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registrationDate;
}
