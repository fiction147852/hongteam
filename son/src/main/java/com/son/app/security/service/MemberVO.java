package com.son.app.security.service;

import lombok.Data;

@Data
public class MemberVO {
	private Integer idNumber;
	private String name;
	private String email;
	private String address;
	private String phone;
	private String password;
	private String birth;
	private String auth;
}
