package com.son.app.security.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		String url = "/lms";

		for (GrantedAuthority authority : authorities) {
			String role = authority.getAuthority();
			if (role.equals("ROLE_STUDENT")) {
				url += "/student";
				break;
			} else if (role.equals("ROLE_PARENT")) {
				url += "/parent";
				break;
			} else if (role.equals("ROLE_INSTRUCTOR")) {
				url += "/instructor";
				break;
			} else if (role.equals("ROLE_ADMIN")) {
				url += "/admin/counselCalendar";
				break;
			}
		}

		response.sendRedirect(url);
	}

}
