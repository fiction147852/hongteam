package com.son.app.member.mapper;

import java.util.List;

import com.son.app.security.service.MemberVO;

public interface AdminMemberMapper {

	//상담일정 전체조회
	public List<MemberVO> memberSelectAll();

	public MemberVO selectMemberList(MemberVO vo);
	
	
}
