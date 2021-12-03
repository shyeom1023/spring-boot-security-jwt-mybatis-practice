package com.security.practice.model.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {

	private Long id;
	private String title;
	private String contente;
	private LocalDate readate;

}
