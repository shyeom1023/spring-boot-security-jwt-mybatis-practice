package com.security.practice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.practice.model.dto.Board;
import com.security.practice.service.BoardService;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/v1")
public class BoardController {

	 private BoardService boardService;

	    @GetMapping("/hello")
	    public ResponseEntity<?> hello() {
	        Map<String, Object> resultMap = new HashMap<>();
	        List<Board> list = boardService.getBoardList();
	        resultMap.put("list", list);
	        return new ResponseEntity<>(resultMap, HttpStatus.OK);
	    }

}
