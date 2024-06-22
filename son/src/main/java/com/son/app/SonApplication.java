package com.son.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages="com.son.app.**.mapper")
public class SonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SonApplication.class, args);
	}

}
