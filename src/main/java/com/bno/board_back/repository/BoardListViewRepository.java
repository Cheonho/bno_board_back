package com.bno.board_back.repository;

import com.bno.board_back.entity.BoardListViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardListViewRepository extends JpaRepository<BoardListViewEntity, Integer> {

    List<BoardListViewEntity> findByOrderByCreateAtDesc();
    List<BoardListViewEntity> findByTitleContainsOrContentContainsOrWriterNicknameContainsOrderByCreateAt(String title, String content, String writerNickname);
    List<BoardListViewEntity> findByWriterNicknameContainsOrderByCreateAtDesc(String writerNickname);
    List<BoardListViewEntity> findByTitleContainsOrderByCreateAtDesc(String title);
    List<BoardListViewEntity> findByContentContainsOrderByCreateAtDesc(String content);
}
