package com.son.app.paper.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PaperVO {
	
	private Integer paperNumber;
	private Integer paperTitle;
	private String producer;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date productionDate;
}
