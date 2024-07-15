package com.son.app.payment.mapper;

import java.util.List;

import com.son.app.lecture.service.RegistrationVO;
import com.son.app.payment.service.AdminPayVO;

public interface AdminPayMapper {

	// 납부 현황 리스트 조회
	List<AdminPayVO> lecturePaySelectAll();

	// 강의에 학생 등록하기
	int payLectureInsertStudent(RegistrationVO registrationVO);
	
	//등록 현황 없데이트
	int payLectureUpdateStatus(RegistrationVO registrationVO);
	
	
}
