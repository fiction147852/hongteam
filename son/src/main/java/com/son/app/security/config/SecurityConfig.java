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
@EnableWebSecurity
public class SecurityConfig{
	
//	@Autowired
//	private SecurityUserService service;
	
	@Autowired
	private CustomUserDetailsService service;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 사이트 위변조 요청 방지
	    http.csrf().disable();
	    
	    http.authorizeHttpRequests()
	    	.antMatchers("/", "/admin/counselUpdateCal", "/mainCounselInsert", "/lectures", "/admission", "/join", "/sec/**", "/login", "/signUp", "/css/**", "/fonts/**", "/images/**", "/js/**", "/vendors/**").permitAll()
	    	.antMatchers("/admin/**").hasRole("ADMIN")
	    	.antMatchers("/student/**").hasRole("STUDENT")
	    	.antMatchers("/parent/**").hasRole("PARENT")
	    	.antMatchers("/instructor/**").hasRole("INSTRUCTOR")
	    	.anyRequest().authenticated();
	    
	    http.formLogin()
	    	.loginPage("/login")
	    	.loginProcessingUrl("/loginProcess")
	    	.successHandler(new CustomLoginSuccessHandler())
	    	.failureHandler(new CustomLoginFailureHandler());
	    
	    http.logout()
	    	.logoutUrl("/logout")
	    	.logoutSuccessUrl("/login")
	    	.invalidateHttpSession(true);
	    
	    http.rememberMe()
	    	.key("son")
	    	.rememberMeParameter("remember")
//	    	.alwaysRemember(true)
	    	.userDetailsService(service);
	    
	    return http.build();
	}
}