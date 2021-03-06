package com.jy.board.common;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jy.board.dto.AttachFileDto;
import com.jy.board.entitiy.AttachFileEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileUtils {
	
public List<AttachFileDto> parseFileInfo(int boardIdx, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		
		// 파일 업로드 요청이 없으면 null 반환
		if(ObjectUtils.isEmpty(multipartHttpServletRequest)) {
			return null;
		}
		
		List<AttachFileDto> fileList = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();	// 현재 날짜 객체
		String folderPath = "upload/" + current.format(formatter);	// 오늘 날짜를 이용해서 폴더를 생성
		File file = new File(folderPath);
		
		if(!file.exists()) {
			file.mkdirs();	// 하위 폴더까지 생성
		}
		
		// 파일 태그의 Name 속성들의 리스트를 가져옴
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

		String newFileName, originalFileExtension, contentType;
		
		while(iterator.hasNext()) {
			List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
			
			for(MultipartFile multipartFile : list) {
				if(!multipartFile.isEmpty()) {
					contentType = multipartFile.getContentType();
					
					if(ObjectUtils.isEmpty(contentType) ) {
						break;
					}
					else {
						if(contentType.contains("image/jpeg")) {
							originalFileExtension = ".jpg";
						} else if(contentType.contains("image/png")) {
							originalFileExtension = ".png";
						} else if(contentType.contains("image/gif")) {
							originalFileExtension = ".gif";
						} else {
							break;
						}
					}
					
					newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
					AttachFileDto attachFileDto = new AttachFileDto();
					attachFileDto.setBoardIdx(boardIdx);
					attachFileDto.setFileSize(multipartFile.getSize());
					attachFileDto.setOriginalFileName(multipartFile.getOriginalFilename());
					attachFileDto.setStoredFilePath(folderPath + "/" + newFileName);
					fileList.add(attachFileDto);
					
					file = new File(folderPath + "/" + newFileName);
					multipartFile.transferTo(file);
				}
			}
		}
		return fileList;
	}
	
	
	// oneToMany 어노테이션으로 인해서 첨부파일 클래스에 게시글 번호를 따로 저장할 필요가 없어짐
	// 파라미터로 게시글 번호를 전달 받지 않도록 수정
	public List<AttachFileEntity> parseFileInfo(MultipartHttpServletRequest multipartHttpServletRequest) {

		// 파일 업로드 요청이 없으면 null 반환
		if (ObjectUtils.isEmpty(multipartHttpServletRequest)) {
			return null;
		}

		List<AttachFileEntity> fileList = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now(); // 현재 날짜 객체
		String folderPath = "upload/" + current.format(formatter); // 오늘 날짜를 이용해서 폴더를 생성
		File file = new File(folderPath);

		if (!file.exists()) {
			file.mkdirs(); // 하위 폴더까지 생성
		}

		// 파일 태그의 Name 속성들의 리스트를 가져옴
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

		String newFileName, originalFileExtension, contentType;

		while (iterator.hasNext()) {
			List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());

			for (MultipartFile multipartFile : list) {
				if (!multipartFile.isEmpty()) {
					try {

						contentType = multipartFile.getContentType();

						if (ObjectUtils.isEmpty(contentType)) {
							break;
						} else {
							if (contentType.contains("image/jpeg")) {
								originalFileExtension = ".jpg";
							} else if (contentType.contains("image/png")) {
								originalFileExtension = ".png";
							} else if (contentType.contains("image/gif")) {
								originalFileExtension = ".gif";
							} else {
								break;
							}
						}

						newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
						AttachFileEntity ataAttachFileEntity = new AttachFileEntity();
						ataAttachFileEntity.setFileSize(multipartFile.getSize());
						ataAttachFileEntity.setOriginalFileName(multipartFile.getOriginalFilename());
						ataAttachFileEntity.setStoredFilePath(folderPath + "/" + newFileName);
						ataAttachFileEntity.setCreatorId("admin");
						fileList.add(ataAttachFileEntity);

						file = new File(folderPath + "/" + newFileName);
						multipartFile.transferTo(file);

					} catch (Exception e) {
						log.info("첨부 파일 파싱 에러 ");
					}

				}
			}
		}
		return fileList;
	}

}
