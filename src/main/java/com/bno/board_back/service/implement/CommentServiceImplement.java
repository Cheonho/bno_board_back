package com.bno.board_back.service.implement;

import com.bno.board_back.dto.object.Comment;
import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.dto.response.comment.GetCommentListResponseDto;
import com.bno.board_back.dto.response.comment.PostCommentResponseDto;
import com.bno.board_back.entity.CommentEntity;
import com.bno.board_back.repository.CommentRepository;
import com.bno.board_back.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImplement implements CommentService {

    private final CommentRepository commentRepository;


    public ResponseEntity<? super GetCommentListResponseDto> getCommentsByBoardNum(Long boardNum) {
        List<CommentEntity> commentList;

        try {
            commentList = commentRepository.findCommentsByBoardNumAndStatusTrue(boardNum);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetCommentListResponseDto.success(commentList);
    }

    @Override
    public ResponseEntity<ResponseDto> deleteCommentById(Long commentNum) {
        try {
            CommentEntity commentEntity = commentRepository.findById(commentNum)
                    .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));

            commentEntity.setStatus(false);
            // 자식댓글도 false처리
            commentRepository.save(commentEntity);

            return ResponseDto.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }


    @Override
    public ResponseEntity<ResponseDto> updateComment(Long commentNum, @RequestBody @Valid Comment comment) {

        try {

            String content = comment.getContent();

            CommentEntity commentEntity = commentRepository.findById(commentNum)
                    .orElseThrow(() -> new RuntimeException("댓글이 존재하지 않습니다."));
            commentEntity.setContent(content);
            commentEntity.setUpdateAt(LocalDateTime.now());
            commentRepository.save(commentEntity);
            return ResponseDto.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }




    @Override
    public ResponseEntity<? super PostCommentResponseDto> postComment(Comment comment) {

        try {
            boolean existedBoard = commentRepository.existsByBoardNum(comment.getBoardNum());
            if (!existedBoard) return PostCommentResponseDto.noExistBoard();

//          boolean existdUser = userRepository.existsByEmail(email);
//          if(!existdUser) return PostCommentResponseDto.noExistUser();

            CommentEntity commentEntity = new CommentEntity(comment);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostCommentResponseDto.success();

    }


}
