package com.son.app.member.service;

import lombok.Data;

@Data
public class ParentVO {
	//학부모
    private Integer ParentNumber;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String password;
    private String birth;
}