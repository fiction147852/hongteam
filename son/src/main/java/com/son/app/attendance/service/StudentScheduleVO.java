package com.son.app.attendance.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class StudentScheduleVO {

    private String type; // 유형 (과제, 시험)
    private String title;

    // 클라이언트 -> 서버
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date start; // 제출(종료) 일자
}
