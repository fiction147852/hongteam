package com.son.app.counsel.service;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data

public class CounselImpossibilityVO {
	private Integer impossibilityNumber;
	private String weekdaysCode;
	private String timeCode;
	@JsonFormat(pattern = "yy/MM/dd", timezone="Asia/Seoul")
	private Date reservationDate;
}
