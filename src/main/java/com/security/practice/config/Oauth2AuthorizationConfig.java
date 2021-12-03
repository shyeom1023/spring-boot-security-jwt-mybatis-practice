package com.security.practice.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.security.practice.service.CustomUserDetailService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
@EnableAuthorizationServer // OAuth2 권한 서버
public class Oauth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

	private final AuthenticationManager authenticationManager;

	private DataSource dataSource;

	private PasswordEncoder passwordEncoder;

	private CustomUserDetailService customUserDetailService;

	/* OAuth2 서버가 작동하기 위한 EndPoint에 대한 정보 설정 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore())
				.authenticationManager(authenticationManager)
				.accessTokenConverter(jwtAccessTokenConverter())
				.userDetailsService(customUserDetailService);
	}

	/* token store로 JWTTokenStore를 사용하겠다 */
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}

	/* JWT 디코딩 하기 위한 설정 */
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey("jwtKey");
		return jwtAccessTokenConverter;
	}

	/* OAuth2 서버가 작동하기 위한 EndPoint에 대한 정보 설정 */
//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		endpoints.authenticationManager(authenticationManager);
//	}

	/* 클라이언트 대한 정보를 설정하는 부분 */
	/* jdbc(DataBase)를 이용하는 방식 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
	}

//	inMemory 방식
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("clientId") // 클라이언트 아이디
//                .secret("{noop}clientSecret") // 클라이언트 시크릿
//                .redirectUris("http://localhost:8080/oauth2/callback")
//                .authorizedGrantTypes("authorization_code", "password")
//                .scopes("read", "write")    // 해당 클라이언트의 접근 범위
//                .accessTokenValiditySeconds(60 * 60 * 4)            // access token 유효 기간 (초 단위)
//                .refreshTokenValiditySeconds(60 * 60 * 24 * 120)    // refresh token 유효 기간 (초 단위)
//                .autoApprove(true);   // OAuth Approval 화면 나오지 않게 처리
//    }
}