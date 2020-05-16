package com.jy.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.board.dto.BoardDto;
import com.jy.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardDto> selectBoardList() {
		return boardMapper.selectBoardList();
	}

	@Override
	public void insertBoard(BoardDto board) {
		boardMapper.insertBoard(board);
	}

	@Override
	public BoardDto selectBoardDetail(int idx) {
		boardMapper.updateHitCount(idx);
		return boardMapper.selectBoardDetail(idx);
	}

	@Override
	public void updateBoard(BoardDto board) {
		boardMapper.updateBoard(board);
	}

	@Override
	public void deleteBoard(int idx) {
		boardMapper.deleteBoard(idx);
	}


}
