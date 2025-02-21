package com.bno.board_back.dto.object;

import com.bno.board_back.entity.BoardEntity;
import com.bno.board_back.entity.BoardListViewEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Boards {

    private int boardNum;
    private String title;
    private String content;
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
