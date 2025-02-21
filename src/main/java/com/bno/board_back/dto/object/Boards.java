package com.bno.board_back.dto.object;

import com.bno.board_back.entity.BoardEntity;
import com.bno.board_back.entity.BoardListViewEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "게시글 작성 DTO")
public class Boards {

    private int boardNum;
    @ApiModelProperty(value = "게시글 제목", required = true)
    @NonNull
    private String title;
    @ApiModelProperty(value = "게시글 내용", required = true)
    @NonNull
    private String content;
    @ApiModelProperty(value = "게시글 작성자", required = true)
    @NonNull
    private String writerEmail;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private int viewCount;
    private int commentCount;
//    private int favoriteCount;

    public Boards(BoardEntity BoardEntity) {
        this.boardNum = BoardEntity.getBoardNum();
        this.title = BoardEntity.getTitle();
        this.content = BoardEntity.getContent();
        this.writerEmail = BoardEntity.getWriterEmail();
        this.createAt = BoardEntity.getCreateAt();
        this.updateAt = BoardEntity.getUpdateAt();
        this.viewCount = BoardEntity.getViewCount();
        this.commentCount = BoardEntity.getCommentCount();
//        this.favoriteCount = BoardEntity.getFavoriteCount();
    }
}
