package com.son.app.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
//	@Autowired
//	private AuthenticationSuccessHandler authenticationSuccessHandler;
//	
//	@Autowired
//	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
//			.anyRequest().authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin()
//			.loginPage("/login")
			.defaultSuccessUrl("/")
			.failureUrl("/login");
		
		return http.build();
	}
}
