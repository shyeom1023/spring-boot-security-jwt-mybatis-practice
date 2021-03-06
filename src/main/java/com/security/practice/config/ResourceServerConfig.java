package com.security.practice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui/index.html",
            // other public endpoints of your API may be appended to this array
            "/v1/callback",
            "/v1/login",
    };

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
//				.antMatchers("/v1/**")
//////				.access("#oauth2.hasScope('write||download')")
//				.hasRole("USER") // 사용자의 authorites 확인
				.antMatchers(AUTH_WHITELIST)
				.permitAll()
				.anyRequest()
				.authenticated();
	}


}
