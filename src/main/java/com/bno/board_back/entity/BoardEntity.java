package com.bno.board_back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="board")
@Table(name="boards")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardNum;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private int viewCount;
    private int commentCount;
//    private int favoriteCount;

    public BoardEntity(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createAt = LocalDateTime.now();
        this.updateAt = null;
        this.viewCount = 0;
        this.commentCount = 0;
//        this.favoriteCount = 0;
    }
}
