package com.son.app.member.mapper;

import java.util.List;

import com.son.app.member.service.ChildInfoVO;

public interface ChildInfoMapper {

	//상담일정 전체조회
	public List<ChildInfoVO> childInfoAll();
}
