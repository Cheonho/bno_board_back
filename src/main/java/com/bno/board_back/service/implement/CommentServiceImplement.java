package com.bno.board_back.service.implement;

import com.bno.board_back.dto.object.Comment;
import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.dto.response.comment.GetCommentListResponseDto;
import com.bno.board_back.dto.response.comment.PostCommentResponseDto;
import com.bno.board_back.entity.CommentEntity;
import com.bno.board_back.mapper.CommentUpdateMapper;
import com.bno.board_back.mapper.CommentWriteMapper;
import com.bno.board_back.repository.BoardRepository;
import com.bno.board_back.repository.CommentRepository;
import com.bno.board_back.repository.UserRepository;
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
    private final CommentWriteMapper commentWriteMapper;
    private final CommentUpdateMapper commentUpdateMapper;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;


    public ResponseEntity<? super GetCommentListResponseDto> getCommentsByBoardNum(Long boardNum) {
        List<CommentEntity> commentList;

        try {
            boolean existedBoard = boardRepository.existsByBoardNum(boardNum);
            if (!existedBoard) return GetCommentListResponseDto.notFoundBoard();

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
            // 자식댓글도 false 처리
            if (commentEntity.getParentNum()==null) {
                List<CommentEntity> childComments =commentRepository.findByParentNum(commentEntity.getCommentNum());
                for (CommentEntity childComment : childComments) {
                    childComment.setStatus(false);
                    commentRepository.save(childComment);
                }
            }
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
            if (comment.getWriterEmail() == null) {
                comment.setWriterEmail("user1@example.com");
            }

            boolean checkUser = userRepository.existsByEmail(comment.getWriterEmail());
            if (!checkUser) return ResponseDto.notFoundUser();

            CommentEntity commentEntity = commentRepository.findById(commentNum)
                    .orElseThrow(() -> new RuntimeException("댓글이 존재하지 않습니다."));
            commentUpdateMapper.updateFormDto(comment, commentEntity);
            commentRepository.save(commentEntity);
            return ResponseDto.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }




    @Override
    public ResponseEntity<? super PostCommentResponseDto> postComment(Comment comment, Long boardNum) {

        try {

            if (comment.getWriterEmail() == null) {
                comment.setWriterEmail("user1@example.com");
            }

            boolean checkUser = userRepository.existsByEmail(comment.getWriterEmail());
            if (!checkUser) return PostCommentResponseDto.notFoundUser();

            boolean existedBoard = boardRepository.existsByBoardNum(boardNum);
            if (!existedBoard) return PostCommentResponseDto.notFoundBoard();

            comment.setBoardNum(boardNum);
            Long parentNum = comment.getParentNum();
            if (parentNum != null) {
                commentRepository.findById(parentNum)
                        .orElseThrow(() -> new RuntimeException("부모 댓글이 존재하지 않습니다."));
                comment.setParentNum(parentNum);
            }

            CommentEntity commentEntity = commentWriteMapper.toEntity(comment);
            commentRepository.save(commentEntity);

            return PostCommentResponseDto.success();
        } catch (Exception e) {
            e.printStackTrace();
            return PostCommentResponseDto.databaseError();
        }
    }

}
