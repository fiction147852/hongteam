package com.son.app.member.mapper;

import java.util.List;

import com.son.app.member.service.StudentVO;

public interface ParentMapper {

	//상담일정 전체조회
	public List<StudentVO> childInfoAll();
}
