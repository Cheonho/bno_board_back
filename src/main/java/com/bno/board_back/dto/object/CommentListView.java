package com.bno.board_back.dto.object;


import com.bno.board_back.entity.CommentEntity;
import com.bno.board_back.entity.CommentListViewEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentListView {

    private Long commentNum;
    private Long boardNum;
    private Long parentNum;
    private String content;
    private String writerEmail;
    private String writerNickname;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Boolean status = true;

    public CommentListView(CommentListViewEntity entity) {
        this.commentNum = entity.getCommentNum();
        this.boardNum = entity.getBoardNum();
        this.parentNum = entity.getParentNum();
        this.content = entity.getContent();
        this.writerEmail = entity.getWriterEmail();
        this.writerNickname = entity.getWriterNickname();
        this.createAt = entity.getCreateAt();
        this.updateAt = entity.getUpdateAt();
        this.status = entity.getStatus();
    }


    public static List<CommentListView> toDtoList(List<CommentListViewEntity> entities) {
        List<CommentListView> comments = new ArrayList<>();
        for (CommentListViewEntity entity : entities) {
            comments.add(new CommentListView(entity));
        }
        return comments;
    }



}
