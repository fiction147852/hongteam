package com.son.app.member.service;

import java.util.List;

import com.son.app.lecture.service.Criteria;
import com.son.app.security.service.MemberVO;

public interface AdminMemberService {

	// 전체 계정 리스트 조회
	public List<MemberVO> memberList(Criteria cri);
	
	// 계정 단건 조회
	public MemberVO memberInfo(int idNumber, String auth);

	// 데이터 갯수
	public int memListPageing(Criteria cri);
}
