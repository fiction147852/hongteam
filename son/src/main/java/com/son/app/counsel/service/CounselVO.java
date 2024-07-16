package com.son.app.counsel.service;

import java.sql.Date;

import lombok.Data;

@Data
public class CounselVO {
	private Integer counselNumber;		//상담 번호
	private String phone;				//연락처
	private String content;				//내용
	private String parentName;			//학부모이름
	private String studentName;			//학생이름
	private String timeCode;			//시간코드
//	@JsonFormat(pattern = "yy/MM/dd", timezone="Asia/Seoul")
	private Date reservationDate;		//예약 일자
	private String reservationDateMain;
	private String reservationCode;		//예약 상태코드
	private String weekdaysCode;
}
