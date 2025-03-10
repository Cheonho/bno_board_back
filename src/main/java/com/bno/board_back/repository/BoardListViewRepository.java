package com.bno.board_back.repository;

import com.bno.board_back.entity.BoardEntity;
import com.bno.board_back.entity.BoardListViewEntity;
import com.bno.board_back.repository.boardCustom.BoardListViewCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardListViewRepository extends JpaRepository<BoardListViewEntity, Long>, BoardListViewCustomRepository {

    Page<BoardListViewEntity> findByOrderByCreateAtDesc(Pageable pageable);

    BoardListViewEntity findByBoardNum(String boardNum);
}
