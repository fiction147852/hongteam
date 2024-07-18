package com.son.app.attendance.service;

import lombok.Data;

@Data
public class AttendanceStatsVO {

    private int totalDays;
    private int attendanceDays;
    private int tardyDays;
    private int earlyLeaveDays;
    private int absentDays;
}
