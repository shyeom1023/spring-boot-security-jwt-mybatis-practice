package com.security.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.practice.model.dto.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserMapper {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User findByPk(String username) {

		User user = userMapper.findByPk(username);

		return user;
	}

}
