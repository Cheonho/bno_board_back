package com.bno.board_back.controller;

import com.bno.board_back.dto.object.Boards;
import com.bno.board_back.dto.response.board.GetBoardListResponseDto;
import com.bno.board_back.dto.response.board.GetSearchBoardListResponseDto;
import com.bno.board_back.dto.response.board.PostWriteBoardResponseDto;
import com.bno.board_back.service.BoardService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/board/")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("board-list")
    public ResponseEntity<? super GetBoardListResponseDto> getBoardList(@RequestParam(value = "page", defaultValue = "0") int page) {
        int size = 3;
        Pageable pageable = PageRequest.of(page, size);
        ResponseEntity<? super GetBoardListResponseDto> response = boardService.getBoardList(pageable);
        return response;
    }

    @GetMapping("search-list/{category}/{searchWord}")
    public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(@PathVariable int category, @PathVariable String searchWord, @RequestParam(value = "page", defaultValue = "0") int page) {
        int size = 3;
        Pageable pageable = PageRequest.of(page, size);
        ResponseEntity<? super GetSearchBoardListResponseDto> response = boardService.getSearchBoardList(category, searchWord, pageable);
        return response;
    }

    @PostMapping("/write")
    public ResponseEntity<? super PostWriteBoardResponseDto> postSaveBoard(@RequestBody Boards board) {
        ResponseEntity<? super PostWriteBoardResponseDto> response = boardService.postWriteBoard(board);
        return response;
    }
}
