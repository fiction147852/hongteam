package com.son.app.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
//	@Autowired
//	private SecurityUserService service;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 사이트 위변조 요청 방지
	    http.csrf().disable();
	    
	    http.authorizeHttpRequests()
//	    	.antMatchers("/lms/admin/**").hasAnyRole("admin")
//	    	.antMatchers("/lms/student/**").hasAnyRole("student")
//	    	.antMatchers("/lms/parent/**").hasAnyRole("parent")
//	    	.antMatchers("/lms/instructor/**").hasAnyRole("instructor")
	    	.anyRequest().permitAll();
	    
	    http.formLogin()
	    	.loginPage("/login")
	    	.loginProcessingUrl("/loginProcess")
	    	.defaultSuccessUrl("/loginTest");
	    
	    http.logout()
	    	.invalidateHttpSession(true);
	    
	    return http.build();
	}
}