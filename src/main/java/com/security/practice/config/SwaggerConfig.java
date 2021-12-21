package com.security.practice.config;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Predicate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.DeferredResult;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Single;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {


	@Bean
	public Docket swagger() {
		return new Docket(DocumentationType.OAS_30).ignoredParameterTypes(java.sql.Date.class)
				.forCodeGeneration(true)
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(apiKey())
				.select()
//				.apis(RequestHandlerSelectors.any())
//				.paths(PathSelectors.any())
//				.paths(PathSelectors.any())
				.paths(PathSelectors.ant("/v1/**"))
				.apis(RequestHandlerSelectors.any())
				.build()
				.apiInfo(apiInfo())
				.useDefaultResponseMessages(false)
				.ignoredParameterTypes(
						WebRequest.class,
						HttpServletRequest.class,
						HttpServletResponse.class,
						HttpSession.class,
						Principal.class,
						Locale.class)
				.enable(true);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("테스트 API 타이틀").description("테스트 API 상세소개 및 사용법 등").version("1.0").build();
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth() {
		List<SecurityReference> securityReferenceList = new ArrayList<SecurityReference>();

		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;

		securityReferenceList.add(new SecurityReference("Authorization", authorizationScopes));

		return securityReferenceList;
	}

	private List<SecurityScheme> apiKey() {
		List<SecurityScheme> securitySchemeList = new ArrayList<SecurityScheme>();

		securitySchemeList.add(new ApiKey("Authorization", "Authorization", "header"));

		return securitySchemeList;
	}

}
