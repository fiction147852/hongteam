package com.son.app.payment.mapper;

import java.util.List;

import com.son.app.payment.service.ParentPayVO;

public interface ParentPayMapper {
	// 지불강의 리스트
	public List<ParentPayVO> lecturePayList();
}
