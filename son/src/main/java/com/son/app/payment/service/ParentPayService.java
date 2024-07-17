package com.son.app.payment.service;

import java.util.List;

public interface ParentPayService {
	//전체조회
	public List<ParentPayVO> LecturePayList(Integer parentNumber);
	
	public int savePaymentInfo(ParentPayVO paymentInfo);
    
    public int updatePaymentStatus(ParentPayVO paymentInfo);
}
