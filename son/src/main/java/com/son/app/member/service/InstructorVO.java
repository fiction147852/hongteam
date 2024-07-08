package com.son.app.member.service;

import lombok.Data;

@Data
public class InstructorVO {
	//교수자
	private Integer instructorNumber;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String subjectCode;
    private String password;
    private String finalEducation;
}
