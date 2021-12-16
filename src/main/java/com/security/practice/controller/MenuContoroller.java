package com.security.practice.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.practice.service.MenuService;

@RestController
@RequestMapping(value = "/v1")
public class MenuContoroller {

	@Autowired
	private MenuService menuService;

	@GetMapping("/menu/{menuName}")
	public String findByMenuName(@PathVariable String menuName) {
        return menuService.findByName(menuName).getId().toString();
    }

}
