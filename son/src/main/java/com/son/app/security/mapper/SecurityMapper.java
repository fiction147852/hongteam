package com.son.app.security.mapper;

import com.son.app.security.service.MemberVO;

public interface SecurityMapper {
	public MemberVO getMemberInfo(String email);
}
