package com.bno.board_back.service;

import com.bno.board_back.dto.response.board.GetBoardListResponseDto;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseEntity<? super GetBoardListResponseDto> getBoardList();
}
