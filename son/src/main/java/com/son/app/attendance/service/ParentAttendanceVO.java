package com.son.app.attendance.service;

import java.util.Date;

import lombok.Data;

@Data
public class ParentAttendanceVO {
    private Integer attendanceNumber;
    private String attendanceCode;
    private int studentNumber;
    private Date attendanceDate;
    private String remark;
    private int lectureNumber;
}
