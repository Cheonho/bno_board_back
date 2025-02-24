package com.bno.board_back.entity;

import io.hypersistence.utils.hibernate.id.Tsid;
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
    private Long boardNum;
    private String title;
    private String content;
    private String writerEmail;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private int viewCount;
    private boolean status;

    public static BoardEntity createBoard(Long boardNum, String title, String content, String writerEmail) {
        return BoardEntity.builder()
                .boardNum(boardNum)
                .title(title)
                .content(content)
                .writerEmail(writerEmail)
                .createAt(LocalDateTime.now())
                .updateAt(null)
                .viewCount(0)
                .status(true)
                .build();
    }
}
