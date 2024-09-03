package com.son.app.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.son.app.security.service.impl.CustomUserDetailsService;

@Configuration
@EnableWebSecurity // Spring Security의 웹 보안 기능을 활성화
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService service; // 해당 빈이 필요한 시점에 Spring 컨테이너에 의해 자동으로 주입된다.

	// 비밀번호를 암호화
	@Bean
	BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	// Spring Security 필터 체인을 구성하고 반환
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// CSRF 공격 방지 활성화. （REST API 서버에서는 비활성화.）
	    http.csrf(csrf -> csrf.disable());

		// 웹 요청에 대한 접근 제어 설정.
	    http.authorizeHttpRequests()
	    	.antMatchers("/", "/admin/counselUpdateCal", "/mainCounselInsert", "/lectures", "/admission", "/join", "/sec/**", "/login", "/signUp", "/css/**", "/fonts/**", "/images/**", "/js/**", "/vendors/**")
				.permitAll()
	    	.antMatchers("/admin/**")
				.hasRole("ADMIN")
	    	.antMatchers("/student/**")
				.hasRole("STUDENT")
	    	.antMatchers("/parent/**")
				.hasRole("PARENT")
	    	.antMatchers("/instructor/**")
				.hasRole("INSTRUCTOR")
	    	.anyRequest().authenticated(); // 위에서 정의된 규칙에 해당하지 않는 모든 요청은 인증된 사용자만 접근할 수 있도록 한다.
	    
	    http.formLogin(login -> login.loginPage("/login")
	    	.loginProcessingUrl("/loginProcess")
	    	.successHandler(new CustomLoginSuccessHandler())
	    	.failureHandler(new CustomLoginFailureHandler()));
	    
	    http.logout(logout -> logout.logoutUrl("/logout")
	    	.logoutSuccessUrl("/login")
	    	.invalidateHttpSession(true));
	    
	    http.rememberMe(me -> me.key("son")
	    	.rememberMeParameter("remember")
//	    	.alwaysRemember(true)
	    	.userDetailsService(service));
	    
	    return http.build();
	}
}