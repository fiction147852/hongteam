package com.son.app.lecture.service;

import lombok.Data;

@Data
public class LectureStudentVO {
	private Integer lectureNumber;
    private Integer studentNumber;
    private String name;
    private String email;
//    private String address;
    private String phone;
    private int parentNumber;
    private String birth;
	
}