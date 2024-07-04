package com.son.app.certification.mapper;

import com.son.app.member.service.StudentVO;
import com.son.app.security.service.MemberVO;

public interface CertMapper {
	public int emailDoubleCheck(String mail);
	public StudentVO childMailCheck(String mail);
	
	public int studentJoin(MemberVO mvo);
	public int parentJoin(MemberVO mvo);
	
	public int setParentNo(int no);
}
