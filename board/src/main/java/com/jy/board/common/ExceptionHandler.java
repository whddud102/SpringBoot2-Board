package com.jy.board.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(NoHandlerFoundException.class)
	public String defaultExceptionHandler(Exception exception) {
		log.info("404 예외 발생 : " + exception);
		return "/error/error_default";
	}
}
