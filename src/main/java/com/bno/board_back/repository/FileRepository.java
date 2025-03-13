package com.bno.board_back.repository;

import com.bno.board_back.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, String> {
    List<FileEntity> findAllByBoardNumAndStatusTrue(String boardNum);
    FileEntity findByBoardNumAndId(String boardNum, String id);
}
