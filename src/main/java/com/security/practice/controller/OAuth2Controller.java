package com.security.practice.controller;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.security.practice.model.vo.Param;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = { "인증" })
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class OAuth2Controller {

	private final RestTemplate restTemplate;

	@ApiOperation(value = "베이직 변환")
	@PostMapping(value = "/basic")
	public String basicAuth(@RequestBody Param.Client param) {

		String msg = param.getUsername() + ":" + param.getPassword();
		Base64 base64 = new Base64();
		String encodedString = new String(base64.encode(msg.getBytes()));
		encodedString = "Basic " + encodedString;

		return encodedString;
	}

	@ApiOperation(value = "토큰 받기")
	@PostMapping(value = "/callback")
	public ResponseEntity<String> callback(@RequestBody Param.Client param) {

		String credentials = param.getUsername() + ":" + param.getPassword();
		String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("Authorization", "Basic " + encodedCredentials);

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("username", "yshss1");
		params.add("password", "duatjrgus1");
		params.add("grant_type", "password");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/oauth/token", request,
				String.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			log.info(response.getBody().toString());
		} else {
			log.info(response.getStatusCode().toString());
		}

		return response;

	}

	@ApiOperation(value = "토큰 받기")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username", value = "yshss1", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "password", value = "duatjrgus1", required = true, dataType = "String", paramType = "query") })
	@GetMapping(value = "/callback")
	public ResponseEntity<String> callbackGet(
			@RequestParam(value = "username") String usernaem,
			@RequestParam(value = "password") String password) {

		String credentials = "clientId2" + ":" + "clientSecret";
		System.out.println(credentials);
		String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("Authorization", "Basic " + encodedCredentials);

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("username", usernaem);
		params.add("password", password);
		params.add("grant_type", "password");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/oauth/token", request,
				String.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			log.info(response.getBody().toString());
		} else {
			log.info(response.getStatusCode().toString());
		}

		return response;

	}

	@ApiOperation(value = "토큰 갱신")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username", value = "clientId", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "password", value = "clientSecret", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "refreshToken", value = "", required = true, dataType = "String", paramType = "query")})
	@GetMapping(value = "/refresh_token")
	public ResponseEntity<String> refeshToken(
			@RequestParam(value = "username") String usernaem,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "refreshToken") String refreshToken) {

		String credentials = usernaem + ":" + password;
		String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "Basic " + encodedCredentials);

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "refresh_token");
		params.add("refresh_token", refreshToken);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, httpHeaders);
		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/oauth/token", request,
				String.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			log.info(response.getBody().toString());
		} else {
			log.info(response.getStatusCode().toString());
		}

		return response;

	}

	@ApiOperation(value = "로그인")
	@PostMapping(value = "/login")
	public ResponseEntity<String> login(@RequestBody(required = true) Param.User param) {
		HttpHeaders httpHeaders = new HttpHeaders();
		MultiValueMap<String, String> parms = new LinkedMultiValueMap<String, String>();
		parms.add("username", param.getUsername());
		parms.add("password", param.getPassword());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String,String>>(parms,httpHeaders);
		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/login", request, String.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			log.info(response.getStatusCode().toString());
			log.info(response.getBody().toString());
		} else {
			log.info(response.getStatusCode().toString());
		}

		return response;

	}

}
