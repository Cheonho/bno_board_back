package com.bno.board_back.repository;

import com.bno.board_back.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    BoardEntity findByBoardNumAndWriterEmail(Long boardNum, String writerEmail);
    BoardEntity findByBoardNum(Long boardNum);

    boolean existsByBoardNum(Long boardNum);
}
