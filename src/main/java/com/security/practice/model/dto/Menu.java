package com.security.practice.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Menu {

	private Long id;
	private Long menuId;
	private Long parentMenuId;
	private String menuPath;
	private String menuName;
	private int menuOrder;

}
