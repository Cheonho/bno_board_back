package com.bno.board_back.repository;

import com.bno.board_back.dto.object.Boards;
import com.bno.board_back.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

}
