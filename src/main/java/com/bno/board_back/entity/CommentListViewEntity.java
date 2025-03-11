package com.bno.board_back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="comment_list_view")
public class CommentListViewEntity {

    @Id
    private String commentNum;
    private String boardNum;
    private String parentNum;
    private String content;
    private String writerEmail;
    private String writerNickname;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Boolean status = true;



}
