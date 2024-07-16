package com.son.app.attendance.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AttendanceSheetVO {

    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+9")
    private Date start;
    private String remark; // 비고
}
