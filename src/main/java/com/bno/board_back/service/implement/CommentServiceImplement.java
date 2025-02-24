package com.bno.board_back.service.implement;
import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.dto.response.comment.GetCommentListResponseDto;
import com.bno.board_back.entity.CommentEntity;
import com.bno.board_back.repository.CommentRepository;
import com.bno.board_back.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImplement implements CommentService {

    private final CommentRepository commentRepository;


    public ResponseEntity<? super GetCommentListResponseDto> getCommentsByBoardNum(Long boardNum) {
        List<CommentEntity> commentList;

        try {
            commentList = commentRepository.findCommentsByBoardNum(boardNum);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databseError();
        }
        return GetCommentListResponseDto.success(commentList);
    }

    @Override
    public ResponseEntity<ResponseDto> deleteCommentById(Long commentNum) {
        try {
            CommentEntity commentEntity = commentRepository.findById(commentNum)
                    .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));

            commentEntity.setStatus(false);
            commentRepository.save(commentEntity);

            return ResponseDto.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databseError();
        }
    }




}
