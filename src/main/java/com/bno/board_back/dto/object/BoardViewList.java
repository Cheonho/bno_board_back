package com.bno.board_back.dto.object;

import com.bno.board_back.entity.BoardListViewEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardViewList {

    private Long boardNum;
    private String title;
    private String content;
    private String writerEmail;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private int viewCount;
    private String writerNickname;

    public BoardViewList(BoardListViewEntity boardListViewEntity) {
        this.boardNum = boardListViewEntity.getBoardNum();
        this.title = boardListViewEntity.getTitle();
        this.content = boardListViewEntity.getContent();
        this.writerEmail = boardListViewEntity.getWriterEmail();
        this.writerNickname = boardListViewEntity.getWriterNickname();
        this.createAt = boardListViewEntity.getCreateAt();
        this.updateAt = boardListViewEntity.getUpdateAt();
        this.viewCount = boardListViewEntity.getViewCount();
    }

    public static List<BoardViewList> getList(List<BoardListViewEntity> boardListViewEntities) {
        List<BoardViewList> list = new ArrayList<>();
        for (BoardListViewEntity boardListViewEntity : boardListViewEntities) {
            BoardViewList board = new BoardViewList(boardListViewEntity);
            list.add(board);
        }
        return list;
    }
}
