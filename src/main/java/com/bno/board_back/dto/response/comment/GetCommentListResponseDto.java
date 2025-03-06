package com.bno.board_back.dto.response.comment;

import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.object.Comment;
import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.entity.CommentEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetCommentListResponseDto extends ResponseDto {

    private List<Comment> commentList;

    public GetCommentListResponseDto(List<CommentEntity> list) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.commentList = Comment.toDtoList(list);
    }

    public static ResponseEntity<GetCommentListResponseDto> success(List<CommentEntity> comments) {
        GetCommentListResponseDto result = new GetCommentListResponseDto(comments);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }




}





