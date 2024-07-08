package com.son.app.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.app.member.mapper.AdminMemberMapper;
import com.son.app.member.service.AdminMemberService;
import com.son.app.security.service.MemberVO;

@Service

public class AdminMemberServiceImpl implements AdminMemberService{

	@Autowired
	AdminMemberMapper adminMemberMapper;
	
	@Override
	public List<MemberVO> memberList() {
		return adminMemberMapper.memberSelectAll();
	}

	@Override
	public MemberVO memberInfo(int idNumber, String auth) {
		MemberVO vo = new MemberVO();
		vo.setIdNumber(idNumber);
		vo.setAuth(auth);
		
		return adminMemberMapper.selectMemberList(vo);
	}
	

	
}
