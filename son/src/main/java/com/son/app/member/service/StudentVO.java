package com.son.app.member.service;

import lombok.Data;

@Data
public class StudentVO {
	//학생
    private Integer studentNumber;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String password;
    private int parentNumber;
    private String birth;
}
