package com.security.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.practice.model.dto.Menu;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MenuService implements MenuMapper {

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public Menu findByName(String menuName) {
		// TODO Auto-generated method stub
		return menuMapper.findByName(menuName);
	}

}
