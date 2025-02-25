package com.bno.board_back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="board_list_view")
@Table(name="board_list_view")
public class BoardListViewEntity {

    @Id
    private Long boardNum;
    private String title;
    private String content;
    private String writerEmail;
    private String writerNickname;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private int viewCount;
}
