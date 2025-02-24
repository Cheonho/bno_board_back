package com.bno.board_back.repository;

import com.bno.board_back.entity.BoardListViewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardListViewRepository extends JpaRepository<BoardListViewEntity, Integer> {

    Page<BoardListViewEntity> findByOrderByCreateAtDesc(Pageable pageable);
    Page<BoardListViewEntity> findByTitleContainsOrContentContainsOrWriterNicknameContainsOrderByCreateAtDesc(String title, String content, String writerNickname, Pageable pageable);
    Page<BoardListViewEntity> findByWriterNicknameContainsOrderByCreateAtDesc(String writerNickname, Pageable pageable);
    Page<BoardListViewEntity> findByTitleContainsOrderByCreateAtDesc(String title, Pageable pageable);
    Page<BoardListViewEntity> findByContentContainsOrderByCreateAtDesc(String content, Pageable pageable);
}
