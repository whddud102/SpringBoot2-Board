package com.jy.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jy.board.entitiy.AttachFileEntity;
import com.jy.board.entitiy.BoardEntity;

public interface JpaBoardRepository extends CrudRepository<BoardEntity, Integer>{
	List<BoardEntity> findAllByOrderByBoardIdxDesc();
	
	// 첨부파일 정보를 가져오는 쿼리는 @Query 어노테이션을 이용해서 직접 작성
	@Query("SELECT file FROM AttachFileEntity file  WHERE board_idx = :boardIdx AND idx = :idx")
	AttachFileEntity findAttachFile(@Param("boardIdx") int boardIdx, @Param("idx") int idx);
	
	
}
