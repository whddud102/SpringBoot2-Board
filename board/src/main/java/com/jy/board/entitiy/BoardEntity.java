package com.jy.board.entitiy;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity	// JPA의 Entity 임을 의미
@Table(name = "t_jpa_board") // 매핑 될 테이블 명시
@NoArgsConstructor
@Data
public class BoardEntity {

	@Id	// 키 속성임을 의미
	@GeneratedValue(strategy = GenerationType.AUTO)	// 기본키 생성 정책을 지정 - DB에서 제공하는 기본키 생성 전략 사용
	private int boardIdx;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String contents;
	
	@Column(nullable = false)
	private int hitCnt = 0;		// 초기값 지정
	
	@Column(nullable = false)
	private String creatorId;
	
	@Column(nullable = false)
	private LocalDateTime createdDateTime = LocalDateTime.now();	// 초기값 지정
	
	private String updaterId;
	
	private LocalDateTime updateDateTime;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)	// 1 : N 관계를 나타내는 어노테이션
	@JoinColumn(name = "board_idx")	// 릴레이션 관계가 있는 테이블 컬럼 지정
	private Collection<AttachFileEntity> fileList;
	
	
	
	
	
	
}
