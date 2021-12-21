package com.security.practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@MapperScan(basePackageClasses = SecurityPracticeApplication.class)
@SpringBootApplication
public class SecurityPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityPracticeApplication.class, args);

		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		System.out.println("endcode = " + passwordEncoder.encode("duatjrgus1"));
	}

}
