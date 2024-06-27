package com.son.app.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String auth = authentication.getAuthorities().toString();
		String url = "/lms";
		
		if(auth.equals("[ROLE_STUDENT]")) {
			url += "/student";
		}else if(auth.equals("[ROLE_PARENT]")) {
			url += "/parent";
		}else if(auth.equals("[ROLE_INSTRUCTOR]")) {
			url += "/instructor";
		}else if(auth.equals("[ROLE_ADMIN]")) {
			url += "/admin";
		}
		
//		System.out.println("ssssssssssssssssssssssssssssssssssssss" + url);
//		System.out.println("ssssssssssssssssssssssssssssssssssssss" + auth);
		
		response.sendRedirect(url);
	}

}
