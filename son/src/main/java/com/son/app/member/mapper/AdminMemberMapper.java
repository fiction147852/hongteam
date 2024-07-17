package com.son.app.member.mapper;

import java.util.List;

import com.son.app.lecture.service.Criteria;
import com.son.app.security.service.MemberVO;

public interface AdminMemberMapper {

	//상담일정 전체조회
	public List<MemberVO> memberSelectAll(Criteria cri);

	public MemberVO selectMemberList(MemberVO vo);

	// 데이터 갯수
	public int selectMemberListPageing(Criteria cri);
	
	
}
