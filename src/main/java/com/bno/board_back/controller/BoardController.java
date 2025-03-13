package com.bno.board_back.controller;

import com.bno.board_back.dto.object.FileDto;
import com.bno.board_back.dto.object.UpdateBoards;
import com.bno.board_back.dto.object.WriteBoards;
import com.bno.board_back.dto.response.board.*;
import com.bno.board_back.dto.object.Comment;
import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.dto.response.board.GetBoardResponseDto;
import com.bno.board_back.dto.response.comment.GetCommentListResponseDto;
import com.bno.board_back.dto.response.comment.PostCommentResponseDto;
import com.bno.board_back.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.bno.board_back.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/board/")
@Tag(name="Board", description = "Board API")
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    public BoardController(BoardService boardService, CommentService commentService) {
        this.boardService = boardService;
        this.commentService = commentService;
    }

    @GetMapping("/{boardNum}")
    public ResponseEntity<? super GetBoardResponseDto> getBoardById(@PathVariable String boardNum) {
        return boardService.getBoardById(boardNum);
    }

    @DeleteMapping("/{boardNum}")
    public ResponseEntity<ResponseDto> deleteBoard(@PathVariable String boardNum) {
        return boardService.deleteBoardById(boardNum);
    }

    @Operation(summary = "게시글 전체 조회", description = "등록된 게시글을 조회 합니다.")
    @GetMapping("board-list")
    public ResponseEntity<? super GetBoardListResponseDto> getBoardList(@RequestParam(value = "page", defaultValue = "0") int page) {
        int size = 5;
        if (page < 1) { page = 1; }
        Pageable pageable = PageRequest.of((page-1), size);
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
        if (page < 1) { page = 1; }
        Pageable pageable = PageRequest.of((page-1), size);
        ResponseEntity<? super GetSearchBoardListResponseDto> response = boardService.getSearchBoardList(category, searchWord, pageable);
        return response;
    }

    @Operation(summary = "한개의 게시글 조회", description = "한개의 게시글 가져오기")
    @GetMapping("detailBoard")
    public ResponseEntity<? super GetDetailBoardResponseDto> getDetailBoard(
            @RequestParam(value = "boardNum", required = true) String boardNum
    ) {
        ResponseEntity<? super GetDetailBoardResponseDto> response = boardService.getDetailBoard(boardNum);
        return response;
    }

    @Operation(summary = "게시글 등록", description = "등록된 게시글을 등록 합니다.")
    @PostMapping(value = "write",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<? super PostWriteBoardResponseDto> postSaveBoard(
            @RequestPart(value = "board", required = true)
            @Parameter(
                    description = "게시판 정보(JSON 형식)",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = WriteBoards.class)
                    )
            )
            WriteBoards board,
            @RequestPart(value = "file", required = false) List<MultipartFile> files
    ) {
        ResponseEntity<? super PostWriteBoardResponseDto> response = boardService.postWriteBoard(board, files);
        return response;
    }

    @Operation(summary = "게시글 수정", description = "등록된 게시글 수정")
    @PutMapping(value = "update",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<? super PutUpdateBoardResponseDto> putUpdateBoard(
            @RequestPart(value = "board", required = true) UpdateBoards board,
            @RequestPart(value = "files", required = false) List<MultipartFile> files,
            @RequestPart(value = "deleteIdList", required = false) List<String> deleteIdList
    ) {
        ResponseEntity<? super PutUpdateBoardResponseDto> response = boardService.putUpdateBoard(board, files, deleteIdList);
        return response;
    }

    @Operation(summary = "조회수 증가", description = "게시글 조회수 증가")
    @PatchMapping("{boardNum}/view")
    public ResponseEntity<? super PatchIncreaseViewCountDto> patchViewBoard(
            @PathVariable String boardNum
    ) {
        ResponseEntity<? super PatchIncreaseViewCountDto> response = boardService.increaseCount(boardNum);
        return response;
    }

    @GetMapping("/{boardNum}/comment")
    public ResponseEntity<? super GetCommentListResponseDto> getCommentById(@PathVariable String boardNum) {
        return commentService.getCommentsByBoardNum(boardNum);
    }

    @DeleteMapping("/{boardNum}/comment/{commentNum}")
    public ResponseEntity<ResponseDto> deleteComment(@PathVariable String commentNum) {
        return commentService.deleteCommentById(commentNum);
    }

    @PostMapping("/{boardNum}/comment")
    public ResponseEntity<? super PostCommentResponseDto> postComment
            (@RequestBody @Valid Comment comment
                    , @PathVariable String boardNum) {
        ResponseEntity<? super PostCommentResponseDto> response = commentService.postComment(comment, boardNum);
        return response;
    }

    @PatchMapping("/{boardNum}/comment/{commentNum}")
    public ResponseEntity<ResponseDto> modifyComment(@PathVariable String commentNum, @RequestBody @Valid Comment comment) {
        return commentService.updateComment(commentNum, comment);
    }
}
