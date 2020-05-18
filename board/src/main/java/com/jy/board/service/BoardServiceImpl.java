package com.jy.board.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jy.board.dto.BoardDto;
import com.jy.board.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService{
	
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
		// boardMapper.insertBoard(board);  임시로 게시글이 등록 되지 않도록 수정
		
		// 파일 업로드 요청이 있을 때만 수행되는 코드
		if(ObjectUtils.isEmpty(multipartHttpServletRequest) == false) {
			
			// 파일 태그의  Name 속성들의  목록을 받아옴
			Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
			String name;
			
			while(iterator.hasNext()) {
				name = iterator.next();	// 파일 태그의 Name 속성을 가져옴
				log.info("-------------- 업로드 요청 파일 정보  ----------------");
				// 해당 Name 값을 가진 파일 태그의 업로드 요청 파일들의 리스트를 가져 옴
				List<MultipartFile> list = multipartHttpServletRequest.getFiles(name);
				for(MultipartFile file : list) {
					log.info("파일 이름 : " + file.getOriginalFilename());
					log.info("파일 크기 : " + file.getSize());
					log.info("파일 타입 : " + file.getContentType() + " \n");
				}
			}
			
			}
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
