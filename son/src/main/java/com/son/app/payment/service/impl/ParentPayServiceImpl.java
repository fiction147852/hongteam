package com.son.app.payment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.payment.mapper.ParentPayMapper;
import com.son.app.payment.service.ParentPayService;
import com.son.app.payment.service.ParentPayVO;
@Service
public class ParentPayServiceImpl implements ParentPayService{
	@Autowired
	ParentPayMapper parentpaymapper;

	@Override
	public List<ParentPayVO> LecturePayList(Integer parentNumber) {
		return parentpaymapper.lecturePayList(parentNumber);
	}

	@Override
	public int savePaymentInfo(ParentPayVO paymentInfo) {
		return parentpaymapper.savePaymentInfo(paymentInfo);
	}

	@Override
	public int updatePaymentStatus(ParentPayVO paymentInfo) {
		return parentpaymapper.updatePaymentStatus(paymentInfo);
	}	
}
