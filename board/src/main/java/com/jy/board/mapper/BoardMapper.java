package com.jy.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jy.board.dto.AttachFileDto;
import com.jy.board.dto.BoardDto;

@Mapper
public interface BoardMapper {
	public List<BoardDto> selectBoardList();

	public void insertBoard(BoardDto board);

	public BoardDto selectBoardDetail(int idx);

	public void updateHitCount(int idx);

	public void updateBoard(BoardDto board);

	public void deleteBoard(int idx);

	public void insertAttachFileList(List<AttachFileDto> list);
	
	public List<AttachFileDto> selectAttachFileList(int boardIdx);
	
	public AttachFileDto selectAttachFileInfo(int idx);
}
