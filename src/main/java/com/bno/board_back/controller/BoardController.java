package com.bno.board_back.controller;

import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.dto.response.board.GetBoardResponseDto;
import com.bno.board_back.dto.response.comment.GetCommentListResponseDto;
import com.bno.board_back.service.BoardService;
import com.bno.board_back.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/board")
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    public BoardController(BoardService boardService, CommentService commentService) {
        this.boardService = boardService;
        this.commentService = commentService;
    }

    @GetMapping("/{boardNum}")
    public ResponseEntity<? super GetBoardResponseDto> getBoardById(@PathVariable Long boardNum) {
        return boardService.getBoardById(boardNum);
    }

    @DeleteMapping("/{boardNum}")
    public ResponseEntity<ResponseDto> deleteBoard(@PathVariable Long boardNum) {
        return boardService.deleteBoardById(boardNum);
    }


    @GetMapping("/{boardNum}/comment")
    public ResponseEntity<? super GetCommentListResponseDto> getCommentById(@PathVariable Long boardNum) {
        return commentService.getCommentsByBoardNum(boardNum);
    }

    @DeleteMapping("/{boardNum}/comment/{commentNum}")
    public ResponseEntity<ResponseDto> deleteComment(@PathVariable Long commentNum) {
        return commentService.deleteCommentById(commentNum);
    }




}
