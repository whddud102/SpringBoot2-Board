package com.jy.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jy.board.common.FileUtils;
import com.jy.board.dto.AttachFileDto;
import com.jy.board.dto.BoardDto;
import com.jy.board.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private FileUtils fileUtils;
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardDto> selectBoardList() {
		return boardMapper.selectBoardList();
	}

	/**
	 * 파입 업로드 요청 객체를 받아서 파일 업로드를 처리 할 수 있도록 수정
	 */
	@Override
	public void insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) {
		
		boardMapper.insertBoard(board);		// board 객체가 DB에 삽입 된 후, idx 번호가 셋팅 되도록 sql 수정
		try {
		
		List<AttachFileDto> list = fileUtils.parseFileInfo(board.getBoardIdx(), multipartHttpServletRequest);
		if(!CollectionUtils.isEmpty(list)) {
			// 첨부 파일들이 존재할 경우에 실행될 코드
			boardMapper.insertAttachFileList(list);
		}
		
		} catch (Exception e) {
			log.info("BoardServiceImpl - insertBoard() 에러 : " + e.getMessage());
		}
	}

	@Override
	public BoardDto selectBoardDetail(int idx) {
		BoardDto board = boardMapper.selectBoardDetail(idx);
		List<AttachFileDto> list = boardMapper.selectAttachFileList(idx);
		board.setFileList(list);
		
		boardMapper.updateHitCount(idx);
		
		return board;
	}

	@Override
	public void updateBoard(BoardDto board) {
		boardMapper.updateBoard(board);
	}

	@Override
	public void deleteBoard(int idx) {
		boardMapper.deleteBoard(idx);
	}

	@Override
	public AttachFileDto selectAttachFileInfo(int idx) {
		return boardMapper.selectAttachFileInfo(idx);
	}


}
