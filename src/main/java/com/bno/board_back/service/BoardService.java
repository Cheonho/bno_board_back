package com.bno.board_back.service;

import com.bno.board_back.dto.object.UpdateBoards;
import com.bno.board_back.dto.object.WriteBoards;
import com.bno.board_back.dto.response.board.GetBoardListResponseDto;
import com.bno.board_back.dto.response.board.GetSearchBoardListResponseDto;
import com.bno.board_back.dto.response.board.PatchUpdateBoardResponseDto;
import com.bno.board_back.dto.response.board.PostWriteBoardResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseEntity<? super GetBoardListResponseDto> getBoardList(Pageable pageable);
    ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(int category, String searchWord, Pageable pageable);
    ResponseEntity<? super PostWriteBoardResponseDto> postWriteBoard(WriteBoards board);
    ResponseEntity<? super PatchUpdateBoardResponseDto> postUpdateBoard(UpdateBoards board);
}
