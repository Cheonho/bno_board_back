package com.bno.board_back.service;

import com.bno.board_back.dto.object.Comment;
import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.dto.response.comment.GetCommentListResponseDto;
import com.bno.board_back.dto.response.comment.PostCommentResponseDto;
import org.springframework.http.ResponseEntity;

public interface CommentService {

    ResponseEntity<? super GetCommentListResponseDto> getCommentsByBoardNum(Long boardNum);
    ResponseEntity<ResponseDto> deleteCommentById(Long commentNum);
    ResponseEntity<? super PostCommentResponseDto> postComment(Comment comment);

}
