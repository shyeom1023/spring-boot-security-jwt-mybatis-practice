package com.security.practice.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Param {

	@ApiModelProperty(value="아이디", example = "clientId", required = true)
	private String username;
	@ApiModelProperty(value="비밀번호", example = "clientSecret", required = true)
	private String password;

}
