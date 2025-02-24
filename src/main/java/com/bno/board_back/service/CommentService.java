package com.bno.board_back.service;

import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.dto.response.comment.GetCommentListResponseDto;
import com.bno.board_back.entity.CommentEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {

    ResponseEntity<? super GetCommentListResponseDto> getCommentsByBoardNum(Long boardNum);

    ResponseEntity<ResponseDto> deleteCommentById(Long commentNum);
}
