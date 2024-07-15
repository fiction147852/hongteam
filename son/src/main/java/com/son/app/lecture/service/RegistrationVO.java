package com.son.app.lecture.service;

import lombok.Data;
@Data
public class RegistrationVO {
	private int registrationNumber;
    private int lectureNumber;
    private int studentNumber;
    private int chargeNumber;
    private String registrationStatus;
    private String subjectName;
}
