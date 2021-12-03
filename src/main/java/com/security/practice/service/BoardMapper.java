package com.security.practice.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.security.practice.model.dto.Board;

@Mapper
public interface BoardMapper {

	public List<Board> getBoardList();
}
