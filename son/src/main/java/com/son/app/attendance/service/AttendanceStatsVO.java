package com.son.app.attendance.service;

import lombok.Data;

@Data
public class AttendanceStatsVO {

    private Integer totalDays;
    private Integer attendanceDays;
    private Integer tardyDays;
    private Integer earlyLeaveDays;
    private Integer absentDays;
}
