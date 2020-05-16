package com.jy.board.dto;

import java.time.LocalDateTime;


import lombok.Data;

@Data
public class BoardDto {
	private int boardIdx;
	private String title;
	private String contents;
	private int hitCnt;
	private String creatorId;
	private LocalDateTime createdDateTime;
	private String updaterId;
	private LocalDateTime updatedDateTime;
	
	
	
}
