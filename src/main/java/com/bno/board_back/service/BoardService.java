package com.bno.board_back.service;

import com.bno.board_back.dto.object.UpdateBoards;
import com.bno.board_back.dto.object.WriteBoards;
import com.bno.board_back.dto.response.board.*;
import org.springframework.data.domain.Pageable;
import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.dto.response.board.GetBoardResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {
    ResponseEntity<? super GetBoardListResponseDto> getBoardList(Pageable pageable);
    ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(int category, String searchWord, Pageable pageable);
    ResponseEntity<? super PostWriteBoardResponseDto> postWriteBoard(WriteBoards board, MultipartFile file);
    ResponseEntity<? super PutUpdateBoardResponseDto> patchUpdateBoard(UpdateBoards board, List<MultipartFile> files, List<String> deleteIdList);
    ResponseEntity<? super PatchIncreaseViewCountDto> increaseCount(String boardNum);
    ResponseEntity<? super GetDetailBoardResponseDto> getDetailBoard(String boardNum);

    ResponseEntity<? super GetBoardResponseDto> getBoardById(String boardNum);

    ResponseEntity<ResponseDto> deleteBoardById(String boardNum);
}
