package com.son.app.attendance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.attendance.mapper.ParentAttendanceMapper;
import com.son.app.attendance.service.ParentAttendanceService;
import com.son.app.attendance.service.ParentAttendanceVO;

@Service
public class ParentAttendanceServiceImpl implements ParentAttendanceService{
	
    @Autowired
    ParentAttendanceMapper parentAttendanceMapper;
    
	@Override
	public List<ParentAttendanceVO> selectAttendanceByParentNumber(int parentNumber) {
		return parentAttendanceMapper.selectAttendanceByParentNumber(parentNumber);
	}

}
