package com.son.app.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.lecture.service.Criteria;
import com.son.app.member.mapper.AdminMemberMapper;
import com.son.app.member.service.AdminMemberService;
import com.son.app.security.service.MemberVO;

@Service

public class AdminMemberServiceImpl implements AdminMemberService{

	@Autowired
	AdminMemberMapper adminMemberMapper;
	
	// 정보 조회 리스트 페이징
	@Override
	public List<MemberVO> memberList(Criteria cri) {
		return adminMemberMapper.memberSelectAll(cri);
	}

	// 데이터 갯수
	@Override
	public int memListPageing(Criteria cri) {
		return adminMemberMapper.selectMemberListPageing(cri);
	}
	
	@Override
	public MemberVO memberInfo(int idNumber, String auth) {
		MemberVO vo = new MemberVO();
		vo.setIdNumber(idNumber);
		vo.setAuth(auth);
		
		return adminMemberMapper.selectMemberList(vo);
	}


	

	
}
