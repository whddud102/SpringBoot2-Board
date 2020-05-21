package com.jy.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jy.board.common.FileUtils;
import com.jy.board.entitiy.AttachFileEntity;
import com.jy.board.entitiy.BoardEntity;
import com.jy.board.repository.JpaBoardRepository;

@Service
public class JpaBoardServiceImpl implements JpaBoardService{

	@Autowired
	private JpaBoardRepository jpaBoardRepository;
	
	@Autowired
	FileUtils fileUtils;
	
	@Override
	public List<BoardEntity> selectBoardList() {
		return jpaBoardRepository.findAllByOrderByBoardIdxDesc();
	}

	@Override
	public void saveBoard(BoardEntity board, MultipartHttpServletRequest multipartHttpServletRequest) {
		board.setCreatorId("admin");	// 작성자는 admin으로 임시 지정
		List<AttachFileEntity> list = fileUtils.parseFileInfo(multipartHttpServletRequest);
		
		if(!CollectionUtils.isEmpty(list)) {	// 등록할 첨부파일이 존재할 때 실행
			board.setFileList(list);
		}
		
		jpaBoardRepository.save(board);
	}
	
	@Override
	public BoardEntity selectBoardDetail(int idx) {
		Optional<BoardEntity> optional = jpaBoardRepository.findById(idx);
		
		// isPresent() : 객체의 값이 존재하는지 검사
		// findById의 결과로 객체가 정상적으로 반환 되었을 때 실행
		if(optional.isPresent()) {
			BoardEntity board = optional.get();
			board.setHitCnt(board.getHitCnt() + 1);	// 조회수 증가
			jpaBoardRepository.save(board);		// 조회수가 증가된 게시글을 update 요청
			
			return board;
		} else {
			throw new NullPointerException();
			
		}
	}

	@Override
	public void deleteBoard(int idx) {
		jpaBoardRepository.deleteById(idx);
	}

	@Override
	public AttachFileEntity selectAttachFileInfo(int boardIdx, int idx) {
		AttachFileEntity attachFile = jpaBoardRepository.findAttachFile(boardIdx, idx);
		return attachFile;
	}

}
