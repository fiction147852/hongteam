package com.son.app.security.service;

import lombok.Data;

@Data
public class MemberVO {

	private Integer idNumber; // 식별자
	private String name; // 이름
	private String email; // 이메일
	private String address; // 주소
	private String phone; // 전화번호
	private String password; // 비밀번호
	private String birth; // 생년월일
	private String auth; // 권한
	private String type; // 사람 유형

	private Integer studentNumber; // 학생 번호
}
