package com.son.app.paper.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.son.app.question.service.QuestionVO;

import lombok.Data;

@Data
public class PaperVO {
	
	private Integer paperNumber;
	private String paperTitle;
	private String producer;
	
	private List<QuestionVO> questions;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date productionDate;
	
}
