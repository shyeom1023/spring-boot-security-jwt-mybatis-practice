package com.security.practice.service;

import org.apache.ibatis.annotations.Mapper;

import com.security.practice.model.dto.Menu;

@Mapper
public interface MenuMapper {

	public Menu findByName(String menuName);
	public Menu findByMenuPath(String menuPath);

}
