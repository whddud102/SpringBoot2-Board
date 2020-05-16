package com.jy.board.service;

import java.util.List;

import com.jy.board.dto.BoardDto;

public interface BoardService {
	public List<BoardDto> selectBoardList();

	public void insertBoard(BoardDto board);

	public BoardDto selectBoardDetail(int idx);

	public void updateBoard(BoardDto board);

	public void deleteBoard(int idx);
}
