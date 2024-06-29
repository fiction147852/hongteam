package com.son.app.counsel.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CounselImpossibility {
	private Integer impossibilityNumber;
	private String weekdaysCode;
	private String timeCode;
	@DateTimeFormat(pattern = "yy/MM/dd")
	private Date reservation;
}
