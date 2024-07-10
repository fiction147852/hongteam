package com.son.app.lecture.online.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class OnlineLectureVO {
	private Integer onlineLectureNumber;
	private String title;
	private String description;
	private String lectureUrl;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date postDate;
	private int views;
	private int lectureNumber;
	private String instructorName;
}
