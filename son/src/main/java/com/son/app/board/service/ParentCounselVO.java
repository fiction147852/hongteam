package com.son.app.board.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ParentCounselVO {
	    private Integer counselNumber;
	    private int number;
	    private String title;
	    private String content;
	    @DateTimeFormat(pattern = "yyyy/MM/dd")
	    private Date writeDate;
	    private int responseStatus;
	    private int lectureNumber;
	    private String responseContent;
	    private int parentNumber;
	    private int instructorNumber;
	    private int studentNumber;
	    
	    private String parentName; // 작성자
	    private String studentName; // 자녀
}
