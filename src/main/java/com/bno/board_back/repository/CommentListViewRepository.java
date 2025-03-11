package com.bno.board_back.repository;

import com.bno.board_back.entity.CommentEntity;
import com.bno.board_back.entity.CommentListViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentListViewRepository extends JpaRepository<CommentListViewEntity, String> {

    List<CommentListViewEntity> findCommentsByBoardNumAndStatusTrueOrderByCreateAtAsc(@Param("boardNum") String boardNum);


}
