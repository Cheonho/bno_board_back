package com.bno.board_back.dto.object;

import com.bno.board_back.entity.BoardEntity;
import com.bno.board_back.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private Long commentNum;
    private Long boardNum;
    private Long parentNum;
    private String content;
    private String writerEmail;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Boolean status = true;

    public Comment(CommentEntity commentEntity) {
        this.commentNum = commentEntity.getCommentNum();
        this.boardNum = commentEntity.getBoardNum();
        this.parentNum = commentEntity.getParentNum();
        this.content = commentEntity.getContent();
        this.writerEmail = commentEntity.getWriterEmail();
        this.createAt = commentEntity.getCreateAt();
        this.updateAt = commentEntity.getUpdateAt();
        this.status = commentEntity.getStatus();
    }

    public static List<Comment> toDtoList(List<CommentEntity> entities) {
        List<Comment> comments = new ArrayList<>();
        for (CommentEntity entity : entities) {
            comments.add(new Comment(entity));
        }
        return comments;
    }


}
