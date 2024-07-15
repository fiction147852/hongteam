package com.son.app.payment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.lecture.service.RegistrationVO;
import com.son.app.payment.mapper.AdminPayMapper;
import com.son.app.payment.service.AdminPayService;
import com.son.app.payment.service.AdminPayVO;
@Service
public class AdminPayServiceImpl implements AdminPayService{

	@Autowired
	AdminPayMapper adminPayMapper;
	
	// 납부 현황 리스트 조회
	@Override
	public List<AdminPayVO> lecturePayList() {
		return adminPayMapper.lecturePaySelectAll();
	}

	// 결제 후 학생 등록 버튼 
	@Override
	public int adminPayInsertStudent(RegistrationVO registrationVO) {
		//등록 현황 없데이트
		adminPayMapper.payLectureUpdateStatus(registrationVO);
		
		// 강의에 학생 등록하기
		return adminPayMapper.payLectureInsertStudent(registrationVO);
	}

	


	
}
