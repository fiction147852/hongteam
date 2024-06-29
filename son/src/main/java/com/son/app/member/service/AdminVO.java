package com.son.app.member.service;

import lombok.Data;

@Data
public class AdminVO {
	private Integer AdminNumber;
    private String name;
    private String email;
    private String phone;
    private String password;
}
