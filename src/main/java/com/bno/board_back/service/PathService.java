package com.bno.board_back.service;

import com.bno.board_back.dto.response.board.GetBoardListResponseDto;
import com.bno.board_back.dto.response.path.GetPathListResponseDto;
import org.springframework.http.ResponseEntity;

public interface PathService {
    ResponseEntity<? super GetPathListResponseDto> getPathList();
}
