package com.bno.board_back.repository;

import com.bno.board_back.dto.UserModel ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer > {

    Optional findByUserId(String userId) ;
    boolean existsByUserId(String userId) ;
    boolean existsByUserName(String userName) ;
}
