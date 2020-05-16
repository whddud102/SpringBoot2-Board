package com.jy.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	}
	
	@PostMapping("/insertBoard") 
	public String insertBoard(BoardDto board)
	{
		boardService.insertBoard(board);
		return "redirect:/board/boardList";
	}
	
	
	@GetMapping("/boardDetail")
	public void boardDetail(Model model, @RequestParam("idx") int idx) {
		BoardDto board =  boardService.selectBoardDetail(idx);
		
		model.addAttribute("board", board);
	}
	
	
}
