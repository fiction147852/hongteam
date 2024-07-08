package com.son.app.attendance.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class StudentScheduleVO {

    private Integer number;
    private Integer lectureNumber;
    private String type; // 유형 (과제, 시험)
    private String title;


    // 서버에서 클라이언트로 JSON 형식의 데이터를 보낼때 날짜 타입의 필드를 지정된 형식의 문자열로 변환한다.
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+9")
    private Date start; // 제출(종료) 일자
}
