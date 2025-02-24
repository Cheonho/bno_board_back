package com.bno.board_back.repository;

import com.bno.board_back.entity.BoardListViewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BoardListViewCustomRepository {

    Page<BoardListViewEntity> findSearch(String title, String content, String writerNickname, Pageable pageable);
}
