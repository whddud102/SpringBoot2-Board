package com.jy.board.service;

import java.util.List;

import com.jy.board.dto.BoardDto;

public interface BoardService {
	List<BoardDto> selectBoardList();
}
