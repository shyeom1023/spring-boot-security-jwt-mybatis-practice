package com.security.practice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.security.practice.model.dto.Board;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardService implements BoardMapper{

    private BoardMapper boardMapper;

    @Override
    public List<Board> getBoardList() {

        return boardMapper.getBoardList();
    }

}