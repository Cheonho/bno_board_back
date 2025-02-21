package com.bno.board_back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="board")
@Table(name="boards")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardNum;
    private String title;
    private String content;
    private String writerEmail;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private int viewCount;
    private int commentCount;
//    private int favoriteCount;

    public static BoardEntity createBoard(String title, String content, String writerEmail) {
        return BoardEntity.builder()
                .title(title)
                .content(content)
                .writerEmail(writerEmail)
                .createAt(LocalDateTime.now())
                .updateAt(null)
                .viewCount(0)
                .commentCount(0)
                .build();
    }
}
