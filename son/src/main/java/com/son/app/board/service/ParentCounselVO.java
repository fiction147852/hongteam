package com.son.app.board.service;

import java.util.Date;

import lombok.Data;

@Data
public class ParentCounselVO {
	    private Integer counselNumber;
	    private int number;
	    private String title;
	    private String content;
	    private Date writeDate;
	    private String responseStatus;
	    private int lectureNumber;
	    private String responseContent;
	    private int parentNumber;
	    private int instructorNumber;
}
