package com.bno.board_back.dto.object;

import com.bno.board_back.entity.BoardListViewEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    private int boardNum;
    private String title;
    private String content;
    private String writerId;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private int viewCount;
    private int commentCount;
    private String writerNickname;

    public Board(BoardListViewEntity boardListViewEntity) {
        this.boardNum = boardListViewEntity.getBoardNum();
        this.title = boardListViewEntity.getTitle();
        this.content = boardListViewEntity.getContent();
        this.writerId = boardListViewEntity.getWriterId();
        this.writerNickname = boardListViewEntity.getWriterNickname();
        this.createAt = boardListViewEntity.getCreateAt();
        this.updateAt = boardListViewEntity.getUpdateAt();
        this.viewCount = boardListViewEntity.getViewCount();
        this.commentCount = boardListViewEntity.getCommentCount();
    }

    public static List<Board> getList(List<BoardListViewEntity> boardListViewEntities) {
        List<Board> list = new ArrayList<>();
        for (BoardListViewEntity boardListViewEntity : boardListViewEntities) {
            Board board = new Board(boardListViewEntity);
            list.add(board);
        }
        return list;
    }
}
