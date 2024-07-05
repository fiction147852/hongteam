package com.son.app.counsel.service;

import lombok.Data;

@Data
public class AdmissionCounselPossibilityVO {
	private Integer possibilityNumber;
	private String weekdaysCode;
	private String timeCode;
	private String startTime;
	private String endTime;
}
