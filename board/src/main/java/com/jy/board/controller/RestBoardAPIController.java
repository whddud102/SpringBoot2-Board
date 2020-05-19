package com.jy.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jy.board.dto.BoardDto;
import com.jy.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/boards/*")
public class RestBoardAPIController {
	
	@Autowired
	private BoardService boardService;
	
	/**
	 * @return 게시글 목록을 JSON으로 반환
	 */
	@GetMapping("/list")
	public List<BoardDto> getBoardList() {
		return boardService.selectBoardList();
	}
	
	/**
	 * @param boardIdx 게시글 번호
	 * @return 특정 게시글을 JSON 으로 반환
	 */
	@GetMapping("/{boardIdx}")
	public BoardDto getBoard(@PathVariable("boardIdx") int boardIdx) {
		return boardService.selectBoardDetail(boardIdx);
	}
			
	
	/**
	 * PUT 요청 처리 메소드, 수정할 게시글 정보와 게시글 번호를 전달 받고 단순히 로그로 출력
	 * @param boardIdx 게시글 번호
	 * @param newBoard 수정할 게시글 내용
	 */
	@PutMapping("/{boardIdx}")
	public void updateBoard(@PathVariable("boardIdx") int boardIdx, @RequestBody BoardDto newBoard) {
		log.info("--- Board 수정 요청 ---");
		log.info("title : " + newBoard.getTitle());
		log.info("contents : " + newBoard.getContents());
	}
	
	
	@PostMapping("/new") 
	public void insertBoard(@RequestBody BoardDto board) {
		log.info("---- Board 등록 요청 -----");
		log.info("title : " + board.getTitle());
		log.info("contents : " + board.getContents());
		boardService.insertBoard(board, null);
	}

}
