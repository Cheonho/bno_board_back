package com.bno.board_back.repository.boardCustom;

import com.bno.board_back.entity.BoardListViewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardListViewCustomRepository {

    Page<BoardListViewEntity> findSearch(String searchWord, int category, Pageable pageable);
}
