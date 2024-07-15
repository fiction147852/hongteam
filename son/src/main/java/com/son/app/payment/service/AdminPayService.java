package com.son.app.payment.service;

import java.util.List;

import com.son.app.lecture.service.RegistrationVO;

public interface AdminPayService {

	// 납부 현황 리스트 조회
	List<AdminPayVO> lecturePayList();

	// 결제 후 학생 등록 버튼 
	int adminPayInsertStudent(RegistrationVO registrationVO);

}
