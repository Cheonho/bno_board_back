package com.bno.board_back.repository.boardCustom.implement;

import com.bno.board_back.entity.BoardListViewEntity;
import com.bno.board_back.entity.QBoardListViewEntity;
import com.bno.board_back.repository.boardCustom.BoardListViewCustomRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardListViewCustomRepositoryImpl implements BoardListViewCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<BoardListViewEntity> findSearch(String searchWord, int category, Pageable pageable) {

        QBoardListViewEntity board = QBoardListViewEntity.boardListViewEntity;

        BooleanBuilder builder = new BooleanBuilder();

        if (category == 1) {
            builder.and(board.title.contains(searchWord)
                    .or(board.content.contains(searchWord))
                    .or(board.writerNickname.contains(searchWord)));
        } else if (category == 2) {
            builder.and(board.writerNickname.contains(searchWord));
        } else if (category == 3) {
            builder.and(board.title.contains(searchWord));
        } else if (category == 4) {
            builder.and(board.content.contains(searchWord));
        }

        var query = queryFactory
                .selectFrom(board)
                .where(builder)
                .orderBy(board.createAt.desc());

        List<BoardListViewEntity> result = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory
                .select(board.count())
                .from(board)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(result, pageable, count != null ? count : 0);
    }
}
