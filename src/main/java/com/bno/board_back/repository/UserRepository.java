package com.bno.board_back.repository;

import com.bno.board_back.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByUserId(String userId);
    Optional findByUserId(String userId) ;
    boolean existsByUserName(String userName) ;
}
