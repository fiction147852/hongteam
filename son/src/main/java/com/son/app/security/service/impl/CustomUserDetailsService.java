package com.son.app.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.son.app.security.mapper.SecurityMapper;
import com.son.app.security.service.CustomUserDetails;
import com.son.app.security.service.MemberVO;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	SecurityMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // 지정된 아이디(이메일)을 기반으로 저장소에서 사용자에 대한 정보를 조회한다.

		MemberVO memberVO = mapper.getMemberInfo(username);
		
		if (memberVO == null) {
			throw new UsernameNotFoundException(username);
		}
		return new CustomUserDetails(memberVO);
	}
}
