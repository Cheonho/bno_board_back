package com.bno.board_back.controller;

import com.bno.board_back.dto.object.UpdateBoards;
import com.bno.board_back.dto.object.WriteBoards;
import com.bno.board_back.dto.response.board.*;
import com.bno.board_back.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board/")
@Tag(name="Board", description = "Board API")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @Operation(summary = "게시글 전체 조회", description = "등록된 게시글을 조회 합니다.")
    @GetMapping("board-list")
    public ResponseEntity<? super GetBoardListResponseDto> getBoardList(@RequestParam(value = "page", defaultValue = "0") int page) {
        int size = 5;
        Pageable pageable = PageRequest.of(page, size);
        ResponseEntity<? super GetBoardListResponseDto> response = boardService.getBoardList(pageable);
        return response;
    }

    @Operation(summary = "게시글 검색", description = "등록된 게시글을 검색 합니다. (category 전체 - 1 / 작성자 - 2 / 제목 - 3 / 내용 - 4)")
    @GetMapping("search-list/{category}/{searchWord}")
    public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(
            @PathVariable int category,
            @PathVariable String searchWord,
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        int size = 5;
        Pageable pageable = PageRequest.of(page, size);
        ResponseEntity<? super GetSearchBoardListResponseDto> response = boardService.getSearchBoardList(category, searchWord, pageable);
        return response;
    }

    @Operation(summary = "게시글 등록", description = "등록된 게시글을 등록 합니다.",
    responses = {
            @ApiResponse(responseCode = "200",
            description = "게시글 작성 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GetBoardListResponseDto.class)
            ))
    })
    @PostMapping("write")
    public ResponseEntity<? super PostWriteBoardResponseDto> postSaveBoard(
            @Valid @RequestBody WriteBoards board
    ) {
        ResponseEntity<? super PostWriteBoardResponseDto> response = boardService.postWriteBoard(board);
        return response;
    }

    @Operation(summary = "게시글 수정", description = "등록된 게시글 수정")
    @PatchMapping("update")
    public ResponseEntity<? super PatchUpdateBoardResponseDto> patchUpdateBoard(
            @Valid @RequestBody UpdateBoards board
    ) {
        ResponseEntity<? super PatchUpdateBoardResponseDto> response = boardService.postUpdateBoard(board);
        return response;
    }

    @Operation(summary = "조회수 증가", description = "게시글 조회수 증가")
    @PatchMapping("{boardNum}/view")
    public ResponseEntity<? super PatchIncreaseViewCountDto> patchViewBoard(
            @PathVariable Long boardNum
    ) {
        ResponseEntity<? super PatchIncreaseViewCountDto> response = boardService.increaseCount(boardNum);
        return response;
    }
}
