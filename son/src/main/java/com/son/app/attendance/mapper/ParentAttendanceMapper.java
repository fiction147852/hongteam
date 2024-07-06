package com.son.app.attendance.mapper;

import java.util.List;

import com.son.app.attendance.service.ParentAttendanceVO;

public interface ParentAttendanceMapper {

	public List<ParentAttendanceVO> selectAttendanceByParentNumber(int parentNumber);

}
