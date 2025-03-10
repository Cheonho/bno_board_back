package com.bno.board_back.dto.object;

import com.bno.board_back.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    private String boardNum;
    private String title;
    private String content;
    private String writerEmail;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private int viewCount;
    private Boolean status = true;

    public Board(BoardEntity BoardEntity) {
        this.boardNum = BoardEntity.getBoardNum();
        this.title = BoardEntity.getTitle();
        this.content = BoardEntity.getContent();
        this.writerEmail = BoardEntity.getWriterEmail();
        this.createAt = BoardEntity.getCreateAt();
        this.updateAt = BoardEntity.getUpdateAt();
        this.viewCount = BoardEntity.getViewCount();
        this.status = BoardEntity.getStatus();
    }


}
