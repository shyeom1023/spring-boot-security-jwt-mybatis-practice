package com.security.practice.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class Param {

	@Getter
	@NoArgsConstructor
	public static class Client{
		@ApiModelProperty(value="아이디", example = "clientId", required = true)
		private String username;
		@ApiModelProperty(value="비밀번호", example = "clientSecret", required = true)
		private String password;
	}

	@Getter
	@NoArgsConstructor
	public static class User{
		@ApiModelProperty(value="아이디", example = "yshss1", required = true)
		private String username;
		@ApiModelProperty(value="비밀번호", example = "duatjrgus1", required = true)
		private String password;
	}



}
