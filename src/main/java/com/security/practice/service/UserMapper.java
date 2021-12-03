package com.security.practice.service;

import org.apache.ibatis.annotations.Mapper;

import com.security.practice.model.dto.User;

@Mapper
public interface UserMapper {

	public User findByPk(String username);

}
