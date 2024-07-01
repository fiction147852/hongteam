package com.son.app.attendance.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class StudentLectureInfoVO {

    private Integer lectureNumber;
    private String subjectName;
    private String detailSubjectName;
    private String lectureLevel;
    private String weekday;
    private String time; // (시작 시간,끝 시간)

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date begin;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date finish;

    // 교수자 이름
    private String name;
}
