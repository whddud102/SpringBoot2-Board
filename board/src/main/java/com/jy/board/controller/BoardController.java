package com.jy.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jy.board.dto.BoardDto;
import com.jy.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/boardList")
	public void openBoardList(Model model) {
		log.info("게시글 조회 요청");
		List<BoardDto> list = boardService.selectBoardList();
		model.addAttribute("list", list);
	}
	
	@GetMapping("/boardWrite")
	public void openBoardWrite() {
		log.info("게시글 작성 페이지 요청");
	}
	
	/**
	 * 파일 업로드 요청을 파라미터로 전달 받도록 수정
	 * @param board 게시글 객체
	 * @param multipartHttpServletRequest 파일 업로드 요청 객체
	 * @return 게시글 목록 화면으로 리다이렉트
	 */
	@PostMapping("/insertBoard") 
	public String insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest)
	{
		boardService.insertBoard(board, multipartHttpServletRequest);
		return "redirect:/board/boardList";
	}
	
	
	@GetMapping("/boardDetail")
	public void boardDetail(Model model, @RequestParam("idx") int idx) {
		BoardDto board =  boardService.selectBoardDetail(idx);
		
		model.addAttribute("board", board);
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(BoardDto board) {
		boardService.updateBoard(board);
		
		return "redirect:/board/boardList";
	}
	
	@PostMapping("/deleteBoard")
	public String deleteBoard(@RequestParam("boardIdx") int idx) {
		boardService.deleteBoard(idx);
		
		return "redirect:/board/boardList";
		
	}
	
	
	
}
