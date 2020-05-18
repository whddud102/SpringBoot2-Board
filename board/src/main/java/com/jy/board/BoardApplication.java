package com.jy.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

// 파일 관련 설정 자동 구성을 제외 시킴, 내가 등록한 CommonsMultiPartResolver를 사용할 것이기 때문
@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
public class BoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}

}
