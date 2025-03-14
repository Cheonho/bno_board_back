package com.bno.board_back.repository;

import com.bno.board_back.entity.RefreshEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshRepository extends JpaRepository<RefreshEntity, String> {

    // email로 refreshtoken 가져 오기
    Optional<RefreshEntity> findByEmail(String email) ;


    // refreshtoken 존재 여부
    boolean existsByRefresh(String refresh) ;

    // refreshtoken 삭제
    // void로 하는 이유 : delete는 반환값이 존재하지 않기 때문이다
    void deleteByRefresh(String refresh) ;


}
