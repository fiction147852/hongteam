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
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO memberVO = mapper.getMemberInfo(username);
		
		if (memberVO == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new CustomUserDetails(memberVO);
	}

}
