package com.jy.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jy.board.entitiy.AttachFileEntity;
import com.jy.board.entitiy.BoardEntity;

public interface JpaBoardService {

	public List<BoardEntity> selectBoardList();
	
	public BoardEntity selectBoardDetail(int idx);
	
	public void saveBoard(BoardEntity board, MultipartHttpServletRequest multipartHttpServletRequest);
	
	public void deleteBoard(int idx);

	public AttachFileEntity selectAttachFileInfo(int boardIdx, int idx);
}
