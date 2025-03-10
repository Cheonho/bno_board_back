package com.bno.board_back.repository;

import com.bno.board_back.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long > {

    Optional<UserEntity> findByEmail(String email) ;
    Optional<UserEntity> findByUserNickname(String userNickname) ;
    Optional<UserEntity> findById(Long id) ;
    Optional<UserEntity> findByPassword(String password) ;
    boolean existsByEmail(String email) ;
    boolean existsByUserNickname(String userNickname) ;

}
