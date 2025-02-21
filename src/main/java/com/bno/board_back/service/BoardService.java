package com.bno.board_back.service;

import com.bno.board_back.dto.object.BoardViewList;
import com.bno.board_back.dto.object.Boards;
import com.bno.board_back.dto.response.board.GetBoardListResponseDto;
import com.bno.board_back.dto.response.board.GetSearchBoardListResponseDto;
import com.bno.board_back.dto.response.board.PostWriteBoardResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseEntity<? super GetBoardListResponseDto> getBoardList(Pageable pageable);
    ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(int category, String searchWord, Pageable pageable);
    ResponseEntity<? super PostWriteBoardResponseDto> postWriteBoard(Boards board);
}
