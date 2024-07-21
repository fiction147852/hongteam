package com.son.app.lecture.service;

import lombok.Data;

@Data
public class Criteria {
	//페이징
	private Integer pageNum;
	private int amount;
	
	private String reservationCode;
	private String auth;
	private String registrationStatus;
	private String subjectName;
	
	//검색
	private String type;
	private String keyword;
	
	public Criteria() {
		this(1,5);
	}
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {
		return type == null? new String[] {} : type.split("");
	}
	public int getPage() {
		return pageNum;
	}
	
	
	
}