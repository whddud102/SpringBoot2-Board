package com.jy.board.common;

import org.springframework.web.bind.annotation.ControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public String defaultExceptionHandler(Exception exception) {
		log.info("예외 발생 : " + exception);
		return "/error/error_default";
	}
}
