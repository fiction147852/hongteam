package com.son.app.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.son.app.member.service.StudentVO;
import com.son.app.security.service.MemberVO;

public interface AdminMemberMapper {

	//상담일정 전체조회
	public List<MemberVO> memberSelectAll();

	public List<MemberVO> selectMemberList(MemberVO vo);
	
	
}
