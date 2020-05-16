package com.jy.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jy.board.dto.BoardDto;

@Mapper
public interface BoardMapper {
	public List<BoardDto> selectBoardList();

	public void insertBoard(BoardDto board);

	public BoardDto selectBoardDetail(int idx);

	public void updateHitCount(int idx);
}
