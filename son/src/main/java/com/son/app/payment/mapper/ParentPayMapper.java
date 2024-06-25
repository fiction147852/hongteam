package com.son.app.payment.mapper;

import java.util.List;

import com.son.app.payment.service.PaymentVO;

public interface ParentPayMapper {
	//학부모 자녀의 강의정보조회
	public List<PaymentVO> ParentPayInfoAll();
}
