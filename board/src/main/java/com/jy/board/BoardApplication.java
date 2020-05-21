package com.jy.board;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing	// 	Audit : 감시하다, JPA 에서 생성/수정 시간 등을 감시하여 자동으로 작성해줌
// 파일 관련 설정 자동 구성을 제외 시킴, 내가 등록한 CommonsMultiPartResolver를 사용할 것이기 때문
@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
// basePackageClasses : JPA 에서 JSR-310(자바 8의 날짜/시간 API)를 에러 없이 사용하기 위해서 지정해 줘야 함
// basePackages : basePackagesClasses 지정 시, 반드시 따로 Entitiy를 스캔할 패키지를 지정해줘야 함
@EntityScan(basePackageClasses = {Jsr310JpaConverters.class} , basePackages = {"com.jy.board.entitiy"})
public class BoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}

}
