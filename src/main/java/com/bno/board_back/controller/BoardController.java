package com.bno.board_back.controller;

import com.bno.board_back.dto.object.Boards;
import com.bno.board_back.dto.response.board.GetBoardListResponseDto;
import com.bno.board_back.dto.response.board.GetSearchBoardListResponseDto;
import com.bno.board_back.dto.response.board.PostWriteBoardResponseDto;
import com.bno.board_back.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(tags="게시판 API")
@Controller
@RequestMapping("/api/v1/board/")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @ApiOperation(value="게시판 전체 조회")
    @GetMapping("board-list")
    public ResponseEntity<? super GetBoardListResponseDto> getBoardList(@RequestParam(value = "page", defaultValue = "0") int page) {
        int size = 3;
        Pageable pageable = PageRequest.of(page, size);
        ResponseEntity<? super GetBoardListResponseDto> response = boardService.getBoardList(pageable);
        return response;
    }

    @ApiOperation(value="게시판 검색 조회")
    @GetMapping("search-list/{category}/{searchWord}")
    public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(
            @ApiParam(value="카테고리 번호", required=true)
            @PathVariable int category,

            @ApiParam(value="검색어", required=true)
            @PathVariable String searchWord,

            @ApiParam(value="페이지", required=true)
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        int size = 3;
        Pageable pageable = PageRequest.of(page, size);
        ResponseEntity<? super GetSearchBoardListResponseDto> response = boardService.getSearchBoardList(category, searchWord, pageable);
        return response;
    }

    @ApiOperation(value="게시글 작성")
    @PostMapping("/write")
    public ResponseEntity<? super PostWriteBoardResponseDto> postSaveBoard(
            @ApiParam(value="게시글", required=true)
            @RequestBody Boards board
    ) {
        ResponseEntity<? super PostWriteBoardResponseDto> response = boardService.postWriteBoard(board);
        return response;
    }
}
