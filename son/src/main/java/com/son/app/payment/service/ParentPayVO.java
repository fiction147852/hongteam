package com.son.app.payment.service;

import java.util.Date;

import lombok.Data;

@Data
public class ParentPayVO {    
	
	// 결제 charge
    private Integer chargeNumber;
    private String chargeCode;
    private String requestDate;
    private String approvalDate;
    private String receiveStatus;
    private String paymentType;
    private Integer studentNumber;
    private String studentName;
    
    // 부모
    private Integer parentNumber;
    
    
    // 강의
    private Integer lectureNumber;
    private String lectureTitle;
    private int lectureCost;
}
