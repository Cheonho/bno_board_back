package com.bno.board_back.repository;

import com.bno.board_back.entity.PathEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PathRepository extends JpaRepository<PathEntity, Long> {
}
