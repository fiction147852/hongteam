package com.son.app.payment.service;

import java.util.Date;

import lombok.Data;

@Data
public class AdminPayVO {    
	
	// 결제 charge
    private Integer chargeNumber;
    private String chargeCode;
    private Date RequestDate;
    private Date approvalDate;
    private String receiveStatus;
    private String paymentType;
    
    // 부모
    private Integer parentNumber;
    // 학생
    private Integer studentNumber;
    private String registrationStatus;
    // 강의
    private Integer lectureNumber;
    private String lectureName;
    private int lectureCost;
    private Date lectureStartDate;
    private Date lectureEndDate;
}
