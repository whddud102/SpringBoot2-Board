package com.jy.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jy.board.dto.AttachFileDto;
import com.jy.board.dto.BoardDto;

public interface BoardService {
	public List<BoardDto> selectBoardList();

	// 파일 업로드 요청 객체를 파라미터로 전달 받도록 수정
	public void insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest);

	public BoardDto selectBoardDetail(int idx);

	public void updateBoard(BoardDto board);

	public void deleteBoard(int idx);
	
	public AttachFileDto selectAttachFileInfo(int idx);
}
