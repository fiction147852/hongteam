package com.son.app.attendance.service;

import java.util.List;

public interface ParentAttendanceService {
	 public List<ParentAttendanceVO> selectAttendanceByParentNumber(int parentNumber);
}
