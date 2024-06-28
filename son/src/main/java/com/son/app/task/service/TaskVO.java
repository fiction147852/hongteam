package com.son.app.task.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Data
public class TaskVO {
	private Integer taskNumber; // 과제번호
	private String title;		// 제목
	private String description; // 설명   
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date postDate;		// 게시 일자
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date submitDeadline;// 제출 기한
	private int lectureNumber;	// 강의 번호
}
