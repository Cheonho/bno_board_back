package com.bno.board_back.entity;

import com.bno.board_back.dto.object.Comment;
import com.bno.board_back.utils.TsidUtilUseSystem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Audited
@Table(name = "comments")
@Entity(name = "comment")
public class CommentEntity {

    @Id
    private String commentNum;
    private String boardNum;
    private String parentNum; // 부모 댓글, 부모 댓글이 없을 시 null
    private String content;
    private String writerEmail;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Boolean status = true;

    public CommentEntity(Comment comment) {
    }

    public CommentEntity(String boardNum, String parentNum, String content, String writerEmail) {
        this.boardNum = boardNum;
        this.parentNum = parentNum;
        this.content = content;
        this.writerEmail = writerEmail;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.status = true;
    }

    @PrePersist
    public void prePersist() {
        if (this.commentNum == null) {
            this.commentNum = String.valueOf(TsidUtilUseSystem.getTsid());
        }
    }

}
