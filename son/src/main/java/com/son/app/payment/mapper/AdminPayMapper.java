package com.son.app.payment.mapper;

import java.util.List;

import com.son.app.lecture.service.RegistrationVO;
import com.son.app.payment.service.AdminPayVO;

public interface AdminPayMapper {

	List<AdminPayVO> lecturePaySelectAll();

	int payLectureInsertStudent(RegistrationVO registrationVO);

	int payLectureUpdateStatus(RegistrationVO registrationVO);
	
	
}
