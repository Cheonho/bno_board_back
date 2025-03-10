package com.bno.board_back.repository;

import com.bno.board_back.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, String> {

    BoardEntity findByBoardNumAndWriterEmail(String boardNum, String writerEmail);
    BoardEntity findByBoardNum(String boardNum);

    boolean existsByBoardNum(String boardNum);
}
