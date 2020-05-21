package com.jy.board.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jy.board.dto.AttachFileDto;
import com.jy.board.dto.BoardDto;
import com.jy.board.entitiy.AttachFileEntity;
import com.jy.board.entitiy.BoardEntity;
import com.jy.board.service.JpaBoardService;

import lombok.extern.slf4j.Slf4j;

/**
 * JPA 데이터베이스를 사용하는 요청을 처리하는 컨트롤러
 * @author JongYoung
 *
 */
@Slf4j
@Controller
@RequestMapping("/jpa/*")
public class JpaBoardController {
	
	@Autowired
	private JpaBoardService jpaBoardService;
	
	@GetMapping("/boards")
	public String openBoardList(Model model) {
		log.info("게시글 조회 요청");
		List<BoardEntity> list = jpaBoardService.selectBoardList();
		model.addAttribute("list", list);
		
		return "/jpa/board/boardList";
	}
	
	@GetMapping("/boards/new")
	public String openBoardWrite() {
		log.info("게시글 작성 페이지 요청");
		
		return "/jpa/board/boardWrite";
	}
	
	/**
	 * 파일 업로드 요청을 파라미터로 전달 받도록 수정
	 * @param board 게시글 객체
	 * @param multipartHttpServletRequest 파일 업로드 요청 객체
	 * @return 게시글 목록 화면으로 리다이렉트
	 */
	@PostMapping("/boards/new") 
	public String insertBoard(BoardEntity board, MultipartHttpServletRequest multipartHttpServletRequest)
	{
		jpaBoardService.saveBoard(board, multipartHttpServletRequest);
		return "redirect:/jpa/boards";
	}
	
	
	@GetMapping("/boards/{boardIdx}")
	public String boardDetail(Model model, @PathVariable("boardIdx") int idx) {
		BoardEntity board =  jpaBoardService.selectBoardDetail(idx);
		
		model.addAttribute("board", board);
		
		return "/jpa/board/boardDetail";
	}
	
	@PutMapping("/boards/{boardIdx}")
	public String updateBoard(@PathVariable("boardIdx") int boardIdx, BoardEntity board) {
		jpaBoardService.saveBoard(board, null);
		
		return "redirect:/jpa/boards";
	}
	
	@DeleteMapping("/boards/{boardIdx}")
	public String deleteBoard(@RequestParam("boardIdx") int idx) {
		jpaBoardService.deleteBoard(idx);
		
		return "redirect:/jpa/boards";
	}
	
	@GetMapping(value = "/boards/file", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(int boardIdx, int idx) {
		AttachFileEntity attachFileDto = jpaBoardService.selectAttachFileInfo(boardIdx, idx);
		String fileName = attachFileDto.getOriginalFileName();
		
		File file = new File(attachFileDto.getStoredFilePath());
		Resource resource = new FileSystemResource(file);
		
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.add("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
			
		} catch (Exception e) {
		}
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	
	
}
