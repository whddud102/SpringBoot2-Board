package com.jy.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jy.board.dto.BoardDto;

@Mapper
public interface BoardMapper {
	List<BoardDto> selectBoardList();
}
