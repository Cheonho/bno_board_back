package com.bno.board_back.service;

import com.bno.board_back.dto.object.Board;
import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.dto.response.board.GetBoardResponseDto;
import org.springframework.http.ResponseEntity;

public interface BoardService {

    ResponseEntity<? super GetBoardResponseDto> getBoardById(Long boardNum);

    ResponseEntity<ResponseDto> deleteBoardById(Long boardNum);
}
