package com.security.practice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

//@EnableResourceServer
//@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//				.antMatchers("/v1/**")
//////				.access("#oauth2.hasScope('write||download')")
//				.hasRole("USER") // 사용자의 authorites 확인
//				.anyRequest()
//				.authenticated();
//	}

}
