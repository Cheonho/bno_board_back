package com.bno.board_back.repository;

import com.bno.board_back.entity.BoardEntity;
import com.bno.board_back.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
        @Query("SELECT c FROM comment c WHERE c.boardNum = :boardNum and c.status = true" +
                " ORDER BY COALESCE(c.parentNum, c.commentNum) ASC, c.commentNum ASC")
        List<CommentEntity> findCommentsByBoardNumAndStatusTrue(@Param("boardNum") Long boardNum);

        List<CommentEntity> findByParentNum(Long parentNum);

        Object findByCommentNumAndWriterEmail(Long commentNum, String writerEmail);
}

