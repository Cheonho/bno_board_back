package com.bno.board_back.dto.response.comment;

import com.bno.board_back.common.ResponseCode;
import com.bno.board_back.common.ResponseMessage;
import com.bno.board_back.dto.object.Comment;
import com.bno.board_back.dto.object.CommentListView;
import com.bno.board_back.dto.response.ResponseDto;
import com.bno.board_back.entity.CommentEntity;
import com.bno.board_back.entity.CommentListViewEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetCommentListResponseDto extends ResponseDto {

    private List<CommentListView> commentList;

    public GetCommentListResponseDto(List<CommentListViewEntity> list) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.commentList = CommentListView.toDtoList(list);
    }

    public static ResponseEntity<GetCommentListResponseDto> success(List<CommentListViewEntity> comments) {
        GetCommentListResponseDto result = new GetCommentListResponseDto(comments);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }




}





