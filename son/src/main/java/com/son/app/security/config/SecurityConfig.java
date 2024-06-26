package com.son.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
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
			.loginPage("/login")
			.and().csrf().disable();
//			.loginProcessingUrl("/login")
//			.loginPage("/login")
//			.defaultSuccessUrl("/")
//			.failureUrl("/login");
		
		return http.build();
	}
}