package com.bno.board_back.dto.object;

import com.bno.board_back.entity.BoardEntity;
import com.bno.board_back.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private String commentNum;
    private String boardNum;
    private String parentNum;
    private String content;
    private String writerEmail;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Boolean status = true;

    public Comment(CommentEntity commentEntity) {
        this.commentNum = commentEntity.getCommentNum();
        this.boardNum = String.valueOf(commentEntity.getBoardNum());
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
