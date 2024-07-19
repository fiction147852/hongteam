package com.son.app.registration.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ChargeVO {
    private Integer chargeNumber;
    private Integer lectureNumber;
    private Integer studentNumber;
    private String chargeCode;
    private String receiveStatus;
    private String registrationStatus;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+9")
    private Date approval_date;
}