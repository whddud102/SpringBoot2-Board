package com.jy.board.dto;

import lombok.Data;

@Data
public class AttachFileDto {
	private int idx;
	private int boardIdx;
	private String originalFileName;
	private String storedFilePath;
	private long fileSize;
}
