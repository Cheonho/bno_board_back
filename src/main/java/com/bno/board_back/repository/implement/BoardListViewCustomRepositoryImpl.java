package com.bno.board_back.repository.implement;

import com.bno.board_back.entity.BoardListViewEntity;
import com.bno.board_back.repository.BoardListViewCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class BoardListViewCustomRepositoryImpl implements BoardListViewCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<BoardListViewEntity> findSearch(String title, String content, String writerNickname, Pageable pageable) {
        return null;
    }
}
