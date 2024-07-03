package com.son.app.certification.mapper;

import com.son.app.member.service.StudentVO;

public interface CertMapper {
	public int emailDoubleCheck(String mail);
	public StudentVO childMailCheck(String mail);
}
