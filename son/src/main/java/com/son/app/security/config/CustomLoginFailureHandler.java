package com.son.app.security.config;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		
		String errMsg = "";
		
		if(exception instanceof UsernameNotFoundException) {
			errMsg += "없는 계정입니다.";
		}else if(exception instanceof BadCredentialsException) {
			errMsg += "비밀번호가 일치하지 않습니다.";
		}else {
			errMsg += "다시 시도해주세요.";
		}
		
		request.setAttribute("error", errMsg); // HttpServletRequest 구현체에 있는 임시 저장소에 데이터 저장
		request.getRequestDispatcher("/login").forward(request, response);
	}

}
