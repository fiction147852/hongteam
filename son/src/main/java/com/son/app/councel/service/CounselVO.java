package com.son.app.councel.service;

import java.sql.Date;

import lombok.Data;

@Data
public class CounselVO {
	private Integer bno;
	private String 학부모이름;
	private String 학생이름;
	private String 내용;
	private Date 날짜;
	private String phoneNumber;
	private String 시간코드;
	private String 예약상태코드;
}
