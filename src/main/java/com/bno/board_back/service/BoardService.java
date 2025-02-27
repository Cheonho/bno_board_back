package com.bno.board_back.service;

import com.bno.board_back.dto.object.UpdateBoards;
import com.bno.board_back.dto.object.WriteBoards;
import com.bno.board_back.dto.response.board.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseEntity<? super GetBoardListResponseDto> getBoardList(Pageable pageable);
    ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(int category, String searchWord, Pageable pageable);
    ResponseEntity<? super PostWriteBoardResponseDto> postWriteBoard(WriteBoards board);
    ResponseEntity<? super PutUpdateBoardResponseDto> postUpdateBoard(UpdateBoards board);
    ResponseEntity<? super PatchIncreaseViewCountDto> increaseCount(Long boardNum);
    ResponseEntity<? super GetDetailBoardResponseDto> getDetailBoard(Long boardNum);
}
